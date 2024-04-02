package com.a502.backend.application.config.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTokenDto{
    private String grantType;
    private String accessToken;
    private String refreshToken;
}