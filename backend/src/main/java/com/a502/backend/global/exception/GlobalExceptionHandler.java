package com.a502.backend.global.exception;


import com.a502.backend.global.error.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.a502.backend.global.exception.ErrorCode.API_ERROR_INPUT_INVALID_VALUE;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleRuntimeException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response =
                ErrorResponse.builder()
                        .errorMessage(errorCode.getMessage())
                        .businessCode(errorCode.getCode())
                        .build();
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response =
                ErrorResponse.of(API_ERROR_INPUT_INVALID_VALUE, e.getBindingResult());
        return ResponseEntity.status(API_ERROR_INPUT_INVALID_VALUE.getStatus()).body(response);
    }
}
