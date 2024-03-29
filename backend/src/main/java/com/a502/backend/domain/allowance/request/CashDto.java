package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashDto {
    private String date;
    private String usage;
    private int amount;

    @Builder
    public CashDto(String date, String usage, int amount) {
        this.date = date;
        this.usage = usage;
        this.amount = amount;
    }
}
