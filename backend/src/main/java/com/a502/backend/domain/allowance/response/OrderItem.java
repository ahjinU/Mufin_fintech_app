package com.a502.backend.domain.allowance.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    private String name;
    private int count;
    private int totalPrice;
    private int unitPrice;

    @Builder

    public OrderItem(String name, int count, int totalPrice, int unitPrice) {
        this.name = name;
        this.count = count;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }
}

