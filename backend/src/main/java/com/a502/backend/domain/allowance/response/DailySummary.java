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
    private boolean isSavingsDay; // '적금내는 날' to English for better readability
    private boolean isLoanPaymentDay; // '대출값는날' to English for better readability

    @Builder
    public DailySummary(String date, int incomeDay, int outcomeDay, boolean isSavingsDay, boolean isLoanPaymentDay) {
        this.date = date;
        this.incomeDay = incomeDay;
        this.outcomeDay = outcomeDay;
        this.isSavingsDay = isSavingsDay;
        this.isLoanPaymentDay = isLoanPaymentDay;
    }

    public void updateTransactionAmount(int amount) {

        if (amount >= 0) {
            this.incomeDay += amount;
            return;
        }

        this.outcomeDay += amount;
    }
    public void markAsSavingsDay() {
        this.isSavingsDay = true;
    }

    public void markAsLoanPaymentDay() {
        this.isLoanPaymentDay = true;
    }
}
