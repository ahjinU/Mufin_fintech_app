package com.a502.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // Global
    API_SUCCESS_DOMAIN_METHOD("EXAMPLE001","예상 메시지입니다."),

    //User
    API_SUCCESS_USER_CHECK_TELEPHONE("U001","사용할 수 있는 번호 입니다."),
    API_SUCCESS_USER_CHECK_EMAIL("U002","사용할 수 있는 이메일 입니다."),
    API_SUCCESS_LOGIN("U003","로그인 되었습니다."),
    API_SUCCESS_SIGNUP("U004","회원가입 되었습니다."),


    // Stock
    API_SUCCESS_STOCK_BUY("S001", "성공적으로 매수 주문했습니다."),
    API_SUCCESS_STOCK_SELL("S001", "성공적으로 매도 주문했습니다."),

    ;
    private final String code;
    private final String message;
}
