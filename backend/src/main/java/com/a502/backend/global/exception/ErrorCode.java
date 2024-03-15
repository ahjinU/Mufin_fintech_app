package com.a502.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),
    API_ERROR_NO_AUTHORIZATION(403, "G002", "권한 없음"),
    ;
    private final int status;
    private final String code;
    private final String message;
}
