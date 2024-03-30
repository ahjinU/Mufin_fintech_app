package com.a502.backend.domain.allowance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class CalendarSummary {
    @JsonProperty
    private List<DailySummary> dayDetailList;
    private int incomeMonth;
    private int outcomeMonth;

    @Builder
    public CalendarSummary(List<DailySummary> dayDetailList, int incomeMonth, int outcomeMonth) {
        this.dayDetailList = dayDetailList;
        this.incomeMonth = incomeMonth;
        this.outcomeMonth = outcomeMonth;
    }

}
