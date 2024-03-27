package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {
    private String date;
    private String time;
    private int price;

    @Builder
    public PaymentInfo(String date, String time, int price) {
        this.date = date;
        this.time = time;
        this.price = price;
    }
}
