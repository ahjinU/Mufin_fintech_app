package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    private String name;
    private int count;
    private int unit;
    private int unitPrice;

    @Builder
    public OrderItem(String name, int count, int unit, int unitPrice) {
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }
}

