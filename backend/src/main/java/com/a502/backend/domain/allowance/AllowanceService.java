package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.CashDetailService;
import com.a502.backend.domain.allowance.request.CalendarDTO;
import com.a502.backend.domain.allowance.response.CalendarSummary;
import com.a502.backend.domain.allowance.response.DailySummary;
import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import com.a502.backend.domain.allowance.response.TransactionDto;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final ReceiptService receiptService;
    private final UserService userService;
    private final CashDetailService cashDetailService;
    private final AccountDetailService accountDetailService;

    public ReceiptResponseDto convert(MultipartFile file) {

        ReceiptResponseDto receiptResponseDto = receiptService.saveReceiptFromImage(file);
        return receiptService.saveReceiptFromImage(file);
    }


    public CalendarSummary getTransactionsForPeriod(CalendarDTO calendarDTO) {

        List<TransactionDto> transactions = new ArrayList<>();

        LocalDateTime start = convertToStartLocalDateTime(calendarDTO.getStartDate());
        LocalDateTime end = convertToEndLocalDateTime(calendarDTO.getEndDate());

        User holderUser = findHolderUser(calendarDTO.getChildUuid());
        List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, start,end);
        List<CashDetail> cashDetails = cashDetailService.getAllCashDetailsByUserAndPeriod(holderUser, start, end);

        transactions.addAll(TransactionDto.convertFromAccountDetails(accountDetails));
        transactions.addAll(TransactionDto.convertFromCashetails(cashDetails));


        CalendarSummary summary = calculateTransactions(transactions);

        return summary;
    }

    private CalendarSummary calculateTransactions(List<TransactionDto> transactions) {
        HashMap<String, DailySummary> map = new HashMap<>();
        CalendarSummary resultSummary= null;

        for (TransactionDto transaction : transactions) {
            String date = formatDateAsIso(transaction.getDate());

            if(map.containsKey(date)){
                DailySummary dailySummary = map.get(date);
                dailySummary.updateTransactionAmount(transaction.getAmount());
                //맵 벨류 갱신
                map.put(date,dailySummary);
            }else{
                DailySummary dailySummary = DailySummary.builder()
                        .date(date)
                        .build();

                dailySummary.updateTransactionAmount(transaction.getAmount());
            }
        }

        //map -> list

        return resultSummary;
    }

    private User findHolderUser(String childUuid) {
        if (childUuid == null)
            return userService.userFindByEmail();

        return userService.findByUserUuid(childUuid);
    }

    private LocalDateTime convertToStartLocalDateTime(String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter).atStartOfDay();
    }

    private LocalDateTime convertToEndLocalDateTime(String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
    }

    public String formatDateAsIso(LocalDateTime localDateTime){
        if (localDateTime == null) {
            System.err.println("널포인터!!");
            return null;
        }

        String formattedDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return formattedDate;
    }
}
