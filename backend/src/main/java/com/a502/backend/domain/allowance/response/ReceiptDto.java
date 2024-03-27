package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.Receipt;
import com.a502.backend.domain.account.dto.DepositWithdrawalAccountDto;
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
    public ReceiptDto(long timestamp, StoreInfo storeInfo, PaymentInfo paymentInfo, List<OrderItem> orderItems, int totalDiscount) {
        this.storeInfo = storeInfo;
        this.paymentInfo = paymentInfo;
        this.orderItems = orderItems;
    }

}

