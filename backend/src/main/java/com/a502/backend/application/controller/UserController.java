package com.a502.backend.application.controller;

import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.facade.UserFacade;
import com.a502.backend.domain.user.dto.*;
import com.a502.backend.domain.user.response.AuthenticationDto;
import com.a502.backend.domain.user.response.UserMyPageResponse;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import static com.a502.backend.application.config.constant.JwtConstant.GRANT_TYPE;
import static com.a502.backend.application.config.constant.JwtConstant.HEADER_STRING;
import static com.a502.backend.global.exception.ErrorCode.API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND;
import static com.a502.backend.global.response.ResponseCode.*;


@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {


        JWTokenDto jwt = userFacade.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        Cookie refreshCookie = createCookie("refreshtoken", jwt.getRefreshToken());
        response.addCookie(refreshCookie);

        httpHeaders.add(HEADER_STRING, GRANT_TYPE + " " + jwt.getAccessToken());

        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_LOGIN);

        return ResponseEntity.ok().headers(httpHeaders).body(apiResponse);

    }

    @PostMapping("/signup/child/check/telephone")
    public ResponseEntity checkChildTelephone(@Valid @RequestBody TelephoneDto telephone, HttpServletResponse response) {

        System.out.println("[UserController]: ");

        return checkTelephoneAndRespond(telephone.getTelephone(), response);
    }

    @PostMapping("/signup/parent/check/telephone")
    public ResponseEntity<ApiResponse<AuthenticationDto>> checkParentTelephone(@Valid @RequestBody TelephoneDto telephone, HttpServletResponse response) {

        System.out.println("[UserController]: ");

        return checkTelephoneAndRespond(telephone.getTelephone(), response);
    }

    @PostMapping("/signup/parent/check/email")
    public ResponseEntity<ApiResponse<AuthenticationDto>> checkParentEmail(@Valid @RequestBody EmailDto email, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[UserController]: /signup/parent/check/email" + email.toString());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        //return checkEmailAndRespond(email.getEmail(), request, response);
        return checkEmailAndRespond(email.getEmail(), response,request);
    }

    @PostMapping("/signup/child/check/email")
    public ResponseEntity<ApiResponse<AuthenticationDto>> checkChildEmail(@Valid @RequestBody EmailDto email, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[UserController]: /signup/child/check/email" + email.toString());
        return checkEmailAndRespond(email.getEmail(),response,request);
    }

    @PostMapping("/signup/parent")
    public ResponseEntity<ApiResponse<String>> signupParent(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("[UserController ]: /signup/parent" + signUpDto.toString());

        return signup(signUpDto, null,  request,response);
    }

    @PostMapping("/signup/child")
    public ResponseEntity<ApiResponse<String>> signupChild(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 현재 인증된 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("[authentication]");
        System.out.println(authentication.toString());
        if (authentication == null)
            throw BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST);

        String parentEmail = authentication.getName(); // Username 추출

        return signup(signUpDto, parentEmail, request, response);
    }

    @PostMapping("/mypage")
    public ResponseEntity<ApiResponse<UserMyPageResponse>> mypage(){
        UserMyPageResponse response = userFacade.mypageInfo();
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_MYPAGE_LIST,response));
    }

    public Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie findCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    findCookie = cookie;
                    return findCookie;
                }
            }
        }

        throw BusinessException.of(API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND);
    }

    public Cookie createCookie(String name, String value) {

        Cookie cookie = new Cookie(name, value);
        // 쿠키 속성 설정
        cookie.setHttpOnly(true);  //httponly 옵션 설정
        cookie.setSecure(true); //https 옵션 설정
        cookie.setPath("/"); // 모든 곳에서 쿠키열람이 가능하도록 설정
        cookie.setMaxAge(60 * 60 * 24); //쿠키 만료시간 설정

        return cookie;
    }

    public Cookie deleteCookie(Cookie cookie) {

        cookie.setHttpOnly(true);  //httponly 옵션 설정
        cookie.setPath("/"); // 모든 곳에서 쿠키열람이 가능하도록 설정
        cookie.setMaxAge(0); //쿠키 만료시간 설정

        return cookie;
    }

    private ResponseEntity<ApiResponse<AuthenticationDto>> checkTelephoneAndRespond(String telephone, HttpServletResponse response) {
        UUID temporaryUserUuid = userFacade.checkDupleTelephone(telephone);

        Cookie uuidCookie = createCookie("authenticationOnlyTelephone", temporaryUserUuid.toString());
        response.addCookie(uuidCookie);

        ApiResponse<AuthenticationDto> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_TELEPHONE);

        return ResponseEntity.ok(apiResponse);
    }

    private ResponseEntity<ApiResponse<AuthenticationDto>> checkEmailAndRespond(String email,HttpServletResponse response, HttpServletRequest request) {

        Cookie authenicationOnlyTelephoneCookie = getCookieByName(request, "authenticationOnlyTelephone");

        if(authenicationOnlyTelephoneCookie==null)
            throw BusinessException.of(ErrorCode.API_ERROR_USER_NOT_COMPLETE_TELEPHONE_CHECK);

        userFacade.checkDupleEmail(authenicationOnlyTelephoneCookie.getValue(), email);

        Cookie uuidCookie = createCookie("temporaryUserUuid", authenicationOnlyTelephoneCookie.getValue());
        response.addCookie(uuidCookie);

        Cookie oldCookie = deleteCookie(authenicationOnlyTelephoneCookie);
        response.addCookie(oldCookie);

        ApiResponse<AuthenticationDto> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_EMAIL);
        return ResponseEntity.ok(apiResponse);
    }

    private ResponseEntity<ApiResponse<String>> signup(SignUpDto signUpDto, String parentName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie temporaryUserCookie = getCookieByName(request, "temporaryUserUuid");

        if(temporaryUserCookie==null)
            throw BusinessException.of(ErrorCode.API_ERROR_USER_NOT_COMPLETE_EMAIL_CHECK);

        System.out.println("부모");
        System.out.println(parentName);
        userFacade.signup(temporaryUserCookie.getValue(), signUpDto, parentName);

        response.addCookie(deleteCookie(temporaryUserCookie));

        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_SIGNUP);
        return ResponseEntity.ok(apiResponse);
    }

}
