package com.a502.backend.domain.allowance.OcrDto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiptDto {
    private StoreInfo storeInfo;
    private PaymentInfo paymentInfo;
    private List<OrderItem> orderItems;

    @Builder
    public ReceiptDto( StoreInfo storeInfo, PaymentInfo paymentInfo, List<OrderItem> orderItems) {
        this.storeInfo = storeInfo;
        this.paymentInfo = paymentInfo;
        this.orderItems = orderItems;
    }

}

