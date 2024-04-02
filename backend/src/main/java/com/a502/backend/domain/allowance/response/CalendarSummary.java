package com.a502.backend.domain.allowance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class CalendarSummary {
    @JsonProperty
    private List<DailySummary> dayDetailList;
    @JsonProperty
    private List<childDto> childs;
    private String holderName;
    private int incomeMonth;
    private int outcomeMonth;

    @Builder
    public CalendarSummary(String holderName,List<DailySummary> dayDetailList, int incomeMonth, int outcomeMonth, List<childDto> childs) {
        this.holderName = holderName;
        this.dayDetailList = dayDetailList;
        this.incomeMonth = incomeMonth;
        this.outcomeMonth = outcomeMonth;
        this.childs=childs;
    }

}
