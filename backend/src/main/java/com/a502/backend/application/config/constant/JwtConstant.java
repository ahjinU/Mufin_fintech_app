package com.a502.backend.application.config.constant;

public class JwtConstant {
    // Access token의 만료 시간 (예: 1시간 = 60 * 60 * 1000 밀리초)
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000; // 1시간
    // Refresh token의 만료 시간 (예: 14일 = 14 * 24 * 60 * 60 * 1000 밀리초)
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000; // 14일
    public static final String HEADER_STRING = "Authorization";
    public static final String GRANT_TYPE = "Bearer";
}
