package com.a502.backend.global.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final String message;
    private T data;

    @JsonCreator
    public ApiResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
    }

    @JsonCreator
    public ApiResponse(ResponseCode responseCode, T data) {
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
