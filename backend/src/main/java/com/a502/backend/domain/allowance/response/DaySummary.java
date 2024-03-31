package com.a502.backend.domain.allowance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DaySummary {

    @JsonProperty("transactionDetails") // JSON 필드 이름 명시적으로 지정
    private List<TransactionDetailDto> transactionDetails;
    private int dayIncome;
    private int dayOutcome;


    @Builder
    public DaySummary(List<TransactionDetailDto> transactionDetails, int dayIncome, int dayOutcome) {
        this.transactionDetails = transactionDetails;
        this.dayIncome = dayIncome;
        this.dayOutcome = dayOutcome;
    }
}
