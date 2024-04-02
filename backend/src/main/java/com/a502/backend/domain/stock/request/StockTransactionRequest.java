package com.a502.backend.domain.stock.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockTransactionRequest {
    String name;
    int price;
    int cnt_total;
}
