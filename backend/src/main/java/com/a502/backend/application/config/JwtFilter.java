package com.a502.backend.application.config;


import com.a502.backend.application.config.constant.JwtConstant;
import com.a502.backend.application.config.generator.JwtProvider;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.a502.backend.global.exception.ErrorCode.API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("[JwtFilter]진입");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            System.out.println("[JwtFilter] 경로확인");
            System.out.println(httpServletRequest.getServletPath());
            // 로그인 경로의 요청에 대해서는 JWT 검증을 생략
            if (isLoginRequest(httpServletRequest.getServletPath()) || isSignupRequest(httpServletRequest.getServletPath())) {
                chain.doFilter(request, response);
                return;
            }

            System.out.println("[JwtFilter] 검증이 필요한 경로");

            // 1. Request Header에서 JWT 토큰 추출
            String token = resolveToken(httpServletRequest);

            System.out.println("[JwtFilter] 토큰: "+token);

            // 2. validateToken으로 토큰 유효성 검사
            if (token != null && jwtTokenProvider.validateToken(token)) {
                System.out.println("[JwtFilter] 유효한 토큰");
                // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                System.out.println("[JwtFilter] authentication: "+authentication);
                System.out.println("[JwtFilter] a");
                System.out.println("[JwtFilter] a");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // 토큰 검증 실패
                System.out.println("[JwtFilter] 토큰 검증 실패");
                throw BusinessException.of(API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND);
            }

            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            // 에러 핸들링: 유효하지 않은 토큰인 경우 401 Unauthorized 응답 반환
            httpServletResponse.sendError(ErrorCode.API_ERROR_USER_ACCESSTOKEN_EXPIRED.getStatus(), e.getMessage());
        }
    }
    private boolean isSignupRequest(String path) {
        System.out.println("[JwtFilter] : 회원가입인지 경로 확인");

        if(path.equals("/api/user/signup/parent/check/telephone")) {
            return true;
        }
        if(path.equals("/api/user/signup/parent/check/email")) {
            return true;
        }
        if(path.equals("/api/auth/signin")) {
            return true;
        }

        return false;
    }

    private boolean isLoginRequest(String path) {
        System.out.println("[JwtFilter] : 로그인인지 경로 확인");

        return path.equals("/api/user/login");
    }
    // Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtConstant.GRANT_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}