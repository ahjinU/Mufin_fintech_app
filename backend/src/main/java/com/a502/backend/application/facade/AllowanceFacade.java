package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.account.CashDetailService;
import com.a502.backend.domain.allowance.MemoService;
import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.domain.allowance.ReceiptService;
import com.a502.backend.domain.allowance.request.*;
import com.a502.backend.domain.allowance.response.*;
import com.a502.backend.domain.loan.LoansService;
import com.a502.backend.domain.savings.SavingsService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class AllowanceFacade {

    private final UserService userService;
    private final AccountDetailService accountDetailService;
    private final CashDetailService cashDetailService;
    private final ReceiptService receiptService;
    private final MemoService memoService;
    private final CodeService codeService;
    private final LoansService loansService;
    private final AccountService accountService;

    public CalendarSummary getTransactionsForPeriod(CalendarDTO calendarDTO) {

        List<TransactionDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(calendarDTO.getStartDate());
        LocalDateTime end = convertToEndLocalDateTime(calendarDTO.getEndDate());

        User holderUser = userService.userFindByEmail();
        List<childDto> childs = null;

        if (userService.isParent(holderUser)) {
            if (calendarDTO.getChildUuid() == null) {
                List<User> childUser = holderUser.getChildrens();

                holderUser = childUser.get(0);

                childs = childDto.convertFromEntitys(childUser);
            } else {
                holderUser = userService.findByUserUuid(convertToUuid(calendarDTO.getChildUuid()));
            }
        }

        List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, start, end);

        transactions.addAll(TransactionDto.convertFromAccountDetails(accountDetails));

        List<CashDetail> cashDetails = cashDetailService.getAllCashDetailsByUserAndPeriod(holderUser, start, end);
        transactions.addAll(TransactionDto.convertFromCashetails(cashDetails));

        Code code = codeService.findTypeCode("진행중");
        List<Loan> loans = loansService.findLoansByUserAndCode(holderUser, code);

        code = codeService.findTypeCode("정상");
        List<Account> savings = accountService.searchActiveSavings(holderUser, code);

        return calculateTransactions(calendarDTO.getStartDate(), calendarDTO.getEndDate(), transactions, childs, holderUser.getName(), loans, savings);
    }

    private LocalDateTime convertToStartLocalDateTime(String startDate) {
        if (startDate == null) {
            throw BusinessException.of(ErrorCode.API_ERROR_NOT_TIME_FORMAT);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter).atStartOfDay();
    }

    private LocalDateTime convertToEndLocalDateTime(String endDate) {
        if (endDate == null) {
            throw BusinessException.of(ErrorCode.API_ERROR_NOT_TIME_FORMAT);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
    }

    public String formatDateAsIso(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String formatDateAsIso(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private User findHolderUser(String childUuid) {
        return userService.findByUserUuid(convertToUuid(childUuid));
    }

    private CalendarSummary calculateTransactions(String start, String end, List<TransactionDto> transactions, List<childDto> childs, String holderName, List<Loan> loans, List<Account> savings) {
        HashMap<String, DailySummary> map = new HashMap<>();

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        for (TransactionDto transaction : transactions) {
            String date = formatDateAsIso(transaction.getDate());
            DailySummary dailySummary = map.computeIfAbsent(date, k -> DailySummary.builder().date(date).build());
            dailySummary.updateTransactionAmount(transaction.getAmount());
        }

        for (Loan loan : loans) {

            LocalDate current = startDate.withDayOfMonth(loan.getPaymentDate()); // 시작 날짜의 월에 대출 납부일 설정
            if (current.isBefore(startDate)) { // 시작 날짜 이전이면, 다음 달로 설정
                current = current.plusMonths(1);
            }

            int cnt = 0;

            while (!current.isAfter(endDate)) { // 종료 날짜까지 반복

                if (cnt + loan.getPaymentNowCnt() > cnt + loan.getPaymentTotalCnt())
                    break;
                ;
                String date = formatDateAsIso(current); // LocalDate를 ISO 형식 문자열로 변환
                DailySummary dailySummary = map.computeIfAbsent(date, k -> DailySummary.builder().date(date).build());
                dailySummary.markAsLoanPaymentDay();

                current = current.plusMonths(1); // 다음 납부일(다음 달)로 이동
                cnt++;
            }
        }

        for (Account saving : savings) {
            LocalDate current = startDate.withDayOfMonth(saving.getPaymentDate()); // 저축 납부일 설정
            if (current.isBefore(startDate)) { // 시작 날짜 이전이면, 다음 달로 설정
                current = current.plusMonths(1);
            }

            int cnt = 0;

            while (!current.isAfter(endDate)) { // 종료 날짜까지 반복
                if (cnt + saving.getPaymentCycle() > saving.getSavings().getPeriod())
                    break;

                String date = formatDateAsIso(current); // LocalDate를 ISO 형식 문자열로 변환
                DailySummary dailySummary = map.computeIfAbsent(date, k -> DailySummary.builder().date(date).build());
                dailySummary.markAsSavingsDay(); // 저축 납부일로 마킹

                current = current.plusMonths(1); // 다음 납부일(다음 달)로 이동

                cnt++;
            }
        }


        List<DailySummary> dailySummaries = new ArrayList<>(map.values());
        Collections.sort(dailySummaries, Comparator.comparing(DailySummary::getDate));

        int monthIncomeTotal = 0;
        int monthOutcomeTotal = 0;

        for (DailySummary summary : dailySummaries) {
            monthIncomeTotal += summary.getIncomeDay();
            monthOutcomeTotal += summary.getOutcomeDay();
        }
        return CalendarSummary.builder().incomeMonth(monthIncomeTotal).outcomeMonth(monthOutcomeTotal).dayDetailList(dailySummaries).childs(childs).holderName(holderName).build();

    }


    public CalendarDetailSummary getTransactionsDetailForMonth(CalendarDTO calendarDTO) {

        List<TransactionDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(calendarDTO.getStartDate());
        LocalDateTime end = convertToEndLocalDateTime(calendarDTO.getEndDate());

        User holderUser = userService.userFindByEmail();

        if (userService.isParent(holderUser)) {
            holderUser = userService.findByUserUuid(convertToUuid(calendarDTO.getChildUuid()));
        }

        List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, start, end);
        List<CashDetail> cashDetails = cashDetailService.getAllCashDetailsByUserAndPeriod(holderUser, start, end);

        transactions.addAll(TransactionDto.convertFromAccountDetails(accountDetails));
        transactions.addAll(TransactionDto.convertFromCashetails(cashDetails));

        Collections.sort(transactions, Comparator.comparing(TransactionDto::getDate));

        int monthIncome = 0;
        int monthOutcome = 0;

        for (TransactionDto transaction : transactions) {
            int price = transaction.getAmount();
            if (price < 0) {
                monthOutcome += price;
                continue;
            }
            monthIncome += price;
        }


        return CalendarDetailSummary.builder()
                .monthIncome(monthIncome)
                .monthOutcome(monthOutcome)
                .transactionDtoList(transactions)
                .build();
    }

    public DaySummary getTransactionsDetailForDay(DayDto dayDto) {
        List<TransactionDetailDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(dayDto.getDate());
        LocalDateTime end = convertToEndLocalDateTime(dayDto.getDate());

        User holderUser = userService.userFindByEmail();

        if (userService.isParent(holderUser)) {
            holderUser = userService.findByUserUuid(convertToUuid(dayDto.getChildUuid()));
        }

        List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, start, end);
        List<CashDetail> cashDetails = cashDetailService.getAllCashDetailsByUserAndPeriod(holderUser, start, end);

        transactions.addAll(TransactionDetailDto.convertFromAccountDetails(accountDetails));
        transactions.addAll(TransactionDetailDto.convertFromCashDetails(cashDetails));

        Collections.sort(transactions, Comparator.comparing(TransactionDetailDto::getDate));


        int dayIncome = 0;
        int dayOutcome = 0;

        for (TransactionDetailDto transaction : transactions) {
            int price = transaction.getAmount();
            if (price < 0) {
                dayOutcome += price;
                continue;
            }
            dayIncome += price;
        }

        Code code = codeService.findTypeCode("정상");
        List<Account> savings = accountService.searchActiveSavings(holderUser,code);

        LocalDate currentDateParsed = LocalDate.parse(dayDto.getDate()); // "YYYY-MM-DD" 형식의 문자열을 LocalDate 객체로 변환
        int currentDayOfMonth = currentDateParsed.getDayOfMonth(); // 현재 날짜의 '일' 부분 추출

        Iterator<Account> savingsIterator = savings.iterator();
        while (savingsIterator.hasNext()) {
            Account account = savingsIterator.next();
            int paymentDay = account.getPaymentDate(); // 적금의 지불 날짜

            if (paymentDay != currentDayOfMonth) {
                savingsIterator.remove(); // 지불 날짜가 현재 '일'과 다르면 목록에서 제거
            }
        }


        code = codeService.findTypeCode("진행중");
        List<Loan> loans = loansService.findLoansByUserAndCode(holderUser, code);

        Iterator<Loan> loanIterator = loans.iterator();
        while (loanIterator.hasNext()) {
            Loan loan = loanIterator.next();
            int paymentDay = loan.getPaymentDate(); // 대출의 납부일

            if (paymentDay != currentDayOfMonth) {
                loanIterator.remove(); // 납부일이 현재 '일'과 다르면 목록에서 제거
            }
        }

        return DaySummary.builder()
                .dayIncome(dayIncome)
                .dayOutcome(dayOutcome)
                .transactionDetails(transactions)
                .savings(SavingsDto.convertFromAcounts(savings))
                .loan(LoanDto.convertFromLoans(loans))
                .build();
    }

    public TransactionDetailDto getTransactionsDetailByUuid(DetailDto detailDto) {
        TransactionDto transactionDto = null;

        if (detailDto.getType().equals("현금")) {
            CashDetail detail = cashDetailService.findTransaction(convertToUuid(detailDto.getTransactionUUID()));
            return TransactionDetailDto.convertFromCashDetail(detail);
        }

        AccountDetail detail = accountDetailService.findTransaction(convertToUuid(detailDto.getTransactionUUID()));
        return TransactionDetailDto.convertFromAccountDetail(detail);
    }

    private UUID convertToUuid(String uuidst) {
        UUID uuid = null;

        try {
            uuid = UUID.fromString(uuidst);
        } catch (IllegalArgumentException e) {
            throw BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST);
        }

        return uuid;
    }

    @Transactional
    public TransactionDetailDto insertCashTransaction(CashDto cashDto) {
        User user = userService.userFindByEmail();
        CashDetail cashDetail = CashDetail.builder()
                .amount(cashDto.getAmount())
                .usageName(cashDto.getUsage())
                .user(user)
                .transAt(convertToStartLocalDateTime(cashDto.getDate()))
                .build();

        CashDetail save = cashDetailService.save(cashDetail);
        return TransactionDetailDto.convertFromCashDetail(save);
    }

    @Transactional
    public TransactionDetailDto insertMemo(MemoDto memoDto) {
        if (memoDto.getType().equals("현금")) {
            CashDetail transaction = cashDetailService.findTransaction(convertToUuid(memoDto.getTransactionUuid()));
            Memo memo = Memo.builder()
                    .content(memoDto.getMemo())
                    .build();
            transaction.updateMemo(memo);
            memoService.save(memo);

            CashDetail detail = cashDetailService.findTransaction(convertToUuid(memoDto.getTransactionUuid()));
            return TransactionDetailDto.convertFromCashDetail(detail);
        } else if (memoDto.getType().equals("계좌")) {
            AccountDetail transaction = accountDetailService.findTransaction(convertToUuid(memoDto.getTransactionUuid()));

            Memo memo = Memo.builder()
                    .content(memoDto.getMemo())
                    .build();

            transaction.updateMemo(memo);
            memoService.save(memo);

            AccountDetail detail = accountDetailService.findTransaction(convertToUuid(memoDto.getTransactionUuid()));
            return TransactionDetailDto.convertFromAccountDetail(detail);

        }

        throw BusinessException.of(ErrorCode.API_ERROR_MEMO_NOT_EXIST_CODE);

    }

    @Transactional
    public ReceiptResponseDto convert(ReceiptRequestDto receiptRequestDto) {
        return receiptService.convert(receiptRequestDto);
    }
}
