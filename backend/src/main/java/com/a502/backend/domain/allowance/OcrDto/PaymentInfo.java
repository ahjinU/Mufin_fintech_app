package com.a502.backend.domain.allowance.OcrDto;

import lombok.*;

@Getter
@ToString
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
