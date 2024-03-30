package com.a502.backend.application.facade;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.Memo;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.CashDetailService;
import com.a502.backend.domain.allowance.MemoService;
import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.domain.allowance.ReceiptService;
import com.a502.backend.domain.allowance.request.*;
import com.a502.backend.domain.allowance.response.*;
import com.a502.backend.domain.user.UserService;
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

    public CalendarSummary getTransactionsForPeriod(CalendarDTO calendarDTO) {

        List<TransactionDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(calendarDTO.getStartDate());
        LocalDateTime end = convertToEndLocalDateTime(calendarDTO.getEndDate());

        System.out.println("시작/끝 타임");
        User holderUser = findHolderUser(calendarDTO.getChildUuid());
        List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, start, end);
        List<CashDetail> cashDetails = cashDetailService.getAllCashDetailsByUserAndPeriod(holderUser, start, end);

        System.out.println("현금/게좌 조회 완료");
        transactions.addAll(TransactionDto.convertFromAccountDetails(accountDetails));
        transactions.addAll(TransactionDto.convertFromCashetails(cashDetails));

        System.out.println("거래내역들 변환 완료");

        CalendarSummary summary = calculateTransactions(transactions);


        System.out.println("계산 완료");
        return summary;
    }

    private LocalDateTime convertToStartLocalDateTime(String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter).atStartOfDay();
    }

    private LocalDateTime convertToEndLocalDateTime(String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
    }

    public String formatDateAsIso(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            System.err.println("널포인터!!");
            return null;
        }

        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private User findHolderUser(String childUuid) {
        if (childUuid == null){
            System.out.println("아이가 없으니 부모로 조회하겠읍니다.");
            return userService.userFindByEmail();
        }

        return userService.findByUserUuid(convertToUuid(childUuid));
    }

    private CalendarSummary calculateTransactions(List<TransactionDto> transactions) {
        HashMap<String, DailySummary> map = new HashMap<>();

        for (TransactionDto transaction : transactions) {
            String date = formatDateAsIso(transaction.getDate());

            DailySummary dailySummary = map.get(date);

            if (dailySummary != null) {
                dailySummary = map.get(date);
            } else {
                dailySummary = DailySummary.builder()
                        .date(date)
                        .build();
            }
            dailySummary.updateTransactionAmount(transaction.getAmount());
            map.put(date, dailySummary);
        }

        List<DailySummary> dailySummaries = new ArrayList<>(map.values());
        Collections.sort(dailySummaries, Comparator.comparing(DailySummary::getDate));


        int monthIncomeTotal = 0;
        int monthOutcomeTotal = 0;

        for (DailySummary summary : dailySummaries) {
            monthIncomeTotal += summary.getIncomeDay();
            monthOutcomeTotal += summary.getOutcomeDay();
        }
        return CalendarSummary.builder().incomeMonth(monthIncomeTotal).outcomeMonth(monthOutcomeTotal).dayDetailList(dailySummaries).build();

    }



    public CalendarDetailSummary getTransactionsDetailForMonth(CalendarDTO calendarDTO) {

        List<TransactionDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(calendarDTO.getStartDate());
        LocalDateTime end = convertToEndLocalDateTime(calendarDTO.getEndDate());

        User holderUser = findHolderUser(calendarDTO.getChildUuid());
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

        User holderUser = findHolderUser(dayDto.getChildUuid());
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


        return DaySummary.builder()
                .dayIncome(dayIncome)
                .dayOutcome(dayOutcome)
                .transactionDetails(transactions)
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
