package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionDto {
    private String counterpartyName;
    private String code;
    private String transactionUuid;
    private int amount;
    private String memo;
    private LocalDateTime date;


    @Builder
    public TransactionDto(String counterpartyName, String code, String transactionUuid, int amount, String memo, LocalDateTime date) {
        this.counterpartyName = counterpartyName;
        this.code = code;
        this.transactionUuid = transactionUuid;
        this.amount = amount;
        this.memo = memo;
        this.date = date;
    }

    public static List<TransactionDto> convertFromAccountDetails(List<AccountDetail> accountDetails) {

        List<TransactionDto> result = new ArrayList<>();
        for(AccountDetail detail : accountDetails){
            TransactionDto transactionDto = createTransactionDto(detail);
            result.add(transactionDto);
        }
        return result;
    }

    private static TransactionDto createTransactionDto(AccountDetail detail) {
        return TransactionDto.builder()
                .transactionUuid(detail.getAccountDetailUuid().toString())
                .code("계좌")
                .amount(detail.getAmount())
                .counterpartyName(detail.getCounterpartyName())
                .build();
    }

    public static List<TransactionDto> convertFromCashetails(List<CashDetail> cashDetails) {
        List<TransactionDto> result = new ArrayList<>();

        for(CashDetail detail : cashDetails){
            TransactionDto transactionDto = convertFromCashetail(detail);
            result.add(transactionDto);
        }
        return result;
    }

    private static TransactionDto convertFromCashetail(CashDetail detail) {
        return TransactionDto.builder()
                .transactionUuid(detail.getCashDetailUuid().toString())
                .code("현금")
                .amount(detail.getAmount())
                .counterpartyName(detail.getUsageName())
                .build();
    }
}
