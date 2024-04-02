package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarDetailSummary {
    private List<TransactionDto> transactionDtoList;
    private int monthIncome;
    private int monthOutcome;

    @Builder
    public CalendarDetailSummary(List<TransactionDto> transactionDtoList, int monthIncome, int monthOutcome) {
        this.transactionDtoList = transactionDtoList;
        this.monthIncome = monthIncome;
        this.monthOutcome = monthOutcome;
    }


}
