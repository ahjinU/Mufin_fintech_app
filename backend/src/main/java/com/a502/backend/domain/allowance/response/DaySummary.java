package com.a502.backend.domain.allowance.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

import java.io.Serial;
import java.util.List;

public class DaySummary {

    @JsonSerialize
    private List<TransactionDetailDto> TransactionDetails;
    private int dayIncome;
    private int dayOutcome;

    @Builder
    public DaySummary(List<TransactionDetailDto> transactionDetails, int dayIncome, int dayOutcome) {
        TransactionDetails = transactionDetails;
        this.dayIncome = dayIncome;
        this.dayOutcome = dayOutcome;
    }
}
