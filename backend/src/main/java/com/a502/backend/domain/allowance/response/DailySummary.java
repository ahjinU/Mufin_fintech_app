package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.AccountDetail;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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


    public void updateTransactionAmount(int amount) {

        if (amount >= 0) {
            this.incomeDay += amount;
            return;
        }

        this.outcomeDay += amount;
    }
}
