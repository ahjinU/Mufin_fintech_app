package com.a502.backend.domain.allowance.response;

import lombok.Builder;

import java.util.List;

public class CalendarSummary {
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
