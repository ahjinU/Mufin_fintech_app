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
    API_SUCCESS_STOCK_SELL("S002", "성공적으로 매도 주문했습니다."),
    API_SUCCESS_STOCK_PRICE_HISTORY_BAR("S003", "기간별 주가 조회(봉 그래프)에 성공하였습니다."),
    API_SUCCESS_STOCK_PRICE_HISTORY_LINE("S004", "기간별 주가 조회(선 그래프)에 성공하였습니다."),
    API_SUCCESS_RANKING_USER("S005", "회원 랭킹조회에 성공했습니다."),
    API_SUCCESS_RANKING_LIST("S006", "1 ~ 10위 랭킹 조회에 성공했습니다."),

    ;

    private final String code;
    private final String message;
}
