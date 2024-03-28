package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailySummary {
   private String date;
   private int incomeDay;
   private int outcomeDay;

   @Builder
    public DailySummary(String date, int incomeDay, int outcomeDay) {
        this.date = date;
        this.incomeDay = incomeDay;
        this.outcomeDay = outcomeDay;
    }
}
