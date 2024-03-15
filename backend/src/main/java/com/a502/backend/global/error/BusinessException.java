package com.a502.backend.global.error;


import com.a502.backend.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public static BusinessException of(ErrorCode errorCode){
        return new BusinessException(errorCode);
    }

    private BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}