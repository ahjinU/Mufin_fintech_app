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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.a502.backend.global.exception.ErrorCode.API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getRequestURI().startsWith("/api/ws-connection")) {
            chain.doFilter(request, response);
            return;
        }


        try {
            log.info("REQUEST PATH : {}", httpServletRequest.getServletPath());

            if (isLoginRequest(httpServletRequest.getServletPath()) || isSignupRequest(httpServletRequest.getServletPath())) {
                chain.doFilter(request, response);
                return;
            }

            String token = resolveToken(httpServletRequest);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw BusinessException.of(API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND);
            }

            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            log.info("AuthenticationException : {}", e);
            httpServletResponse.sendError(ErrorCode.API_ERROR_USER_ACCESSTOKEN_EXPIRED.getStatus(), e.getMessage());
        }
    }

    private boolean isSignupRequest(String path) {

        if (path.equals("/api/user/signup/parent/check/telephone")) {
            return true;
        }
        if (path.equals("/api/user/signup/parent/check/email")) {
            return true;
        }
        if (path.equals("/api/user/signup/parent")) {
            return true;
        }

        return false;
    }

    private boolean isLoginRequest(String path) {
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