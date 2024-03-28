package com.a502.backend.application.controller;

import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtProvider;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.application.facade.KeypadFacade;
import com.a502.backend.domain.user.dto.*;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.domain.user.response.AuthenticationDto;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.a502.backend.global.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final KeypadFacade keypadFacade;

    private final JwtProvider tokenProvider;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {

        JWTokenDto jwt = userService.login(loginDto);

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
        return checkEmailAndRespond(email.getEmail(), email.getTemporaryUserUuid().toString());
    }

    @PostMapping("/signup/child/check/email")
    public ResponseEntity<ApiResponse<AuthenticationDto>> checkChildEmail(@Valid @RequestBody EmailDto email, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[UserController]: /signup/child/check/email" + email.toString());
        return checkEmailAndRespond(email.getEmail(), email.getTemporaryUserUuid().toString());
    }

    @PostMapping("/signup/parent")
    public ResponseEntity<ApiResponse<String>> signupParent(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[UserController ]: /signup/parent" + signUpDto.toString());

        return signup(signUpDto, null, request, response);
    }

    @PostMapping("/signup/child")
    public ResponseEntity<ApiResponse<String>> signupChild(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request, HttpServletResponse response) {

        // 현재 인증된 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("[authentication]");
        System.out.println(authentication.toString());
        if (authentication == null)
            throw BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST);

        String parentEmail = authentication.getName(); // Username 추출

        return signup(signUpDto, parentEmail, request, response);
    }

    /*public User userFindByEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Username 추출

        User user = UserRepository.findByEmail(email);

        return user;
    }*/


    public Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie findCookie = null;
        if (cookies.length < 1)
            System.out.println("쿠키가 엄서용");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    findCookie = cookie;
                    System.out.println("쿠키 찾았다 !");
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
        UUID temporaryUserUuid = userService.checkDupleTelephone(telephone);

        /*\

        Cookie uuidCookie = createCookie("authenticationOnlyTelephone", temporaryUserUuid.toString());
        response.addCookie(uuidCookie);

         */
        AuthenticationDto authenticationDto = AuthenticationDto.builder()
                .temporaroyUserUuid(temporaryUserUuid.toString())
                .build();
        ApiResponse<AuthenticationDto> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_TELEPHONE, authenticationDto);

        return ResponseEntity.ok(apiResponse);
    }

    private ResponseEntity<ApiResponse<AuthenticationDto>> checkEmailAndRespond(String email,String uuid) {

        /*Cookie authenicationOnlyTelephoneCookie = getCookieByName(request, "authenticationOnlyTelephone");

        userService.checkDupleEmail(authenicationOnlyTelephoneCookie.getValue(), email);

        Cookie uuidCookie = createCookie("temporaryUserUuid", authenicationOnlyTelephoneCookie.getValue());
        response.addCookie(uuidCookie);

        Cookie oldCookie = deleteCookie(authenicationOnlyTelephoneCookie);
        response.addCookie(oldCookie);

        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_EMAIL);*/

        userService.checkDupleEmail(uuid, email);
        AuthenticationDto authentication = AuthenticationDto.builder()
                .temporaroyUserUuid(uuid.toString())
                .build();

        ApiResponse<AuthenticationDto> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_EMAIL,authentication);
        return ResponseEntity.ok(apiResponse);
    }

    private ResponseEntity<ApiResponse<String>> signup(SignUpDto signUpDto, String parentName, HttpServletRequest request, HttpServletResponse response) {
        Cookie temporaryUserCookie = getCookieByName(request, "temporaryUserUuid");

        System.out.println("부모");
        System.out.println(parentName);
        userService.signup(temporaryUserCookie.getValue(), signUpDto, parentName);

        response.addCookie(deleteCookie(temporaryUserCookie));

        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_SIGNUP);
        return ResponseEntity.ok(apiResponse);
    }

}
