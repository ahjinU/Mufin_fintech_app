package com.a502.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // Global
    API_SUCCESS_DOMAIN_METHOD("EXAMPLE001","예상 메시지입니다."),



    // Stock
    API_SUCCESS_STOCK_BUY("S001", "성공적으로 매수 주문했습니다."),

    ;
    private final String code;
    private final String message;
}
