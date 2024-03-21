package com.a502.backend.application.controller;

import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtProvider;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.domain.user.dto.EmailDto;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.domain.user.dto.SignUpDto;
import com.a502.backend.domain.user.dto.TelephoneDto;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

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


    private final JwtProvider tokenProvider;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {

        JWTokenDto jwt = userService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        Cookie refreshCookie = createCookie("refreshtoken",jwt.getRefreshToken());
        response.addCookie(refreshCookie);

        httpHeaders.add(HEADER_STRING, GRANT_TYPE + " " + jwt.getAccessToken());

        return ResponseEntity.ok().headers(httpHeaders).body("success!");
    }

    @PostMapping("/signup/child")
    public ResponseEntity signUpChild(@Valid @RequestBody LoginDto loginDto,HttpServletResponse response) {

        System.out.println("[UserController]");
        System.out.println(loginDto.toString());

        System.out.println(5);
        HttpHeaders httpHeaders = new HttpHeaders();

        // response header에 jwt token에 넣어줌
       // response.addCookie();

        return ResponseEntity.ok().headers(httpHeaders).body(API_SUCCESS_LOGIN);
    }

    @PostMapping("/signup/telephone")
    public ResponseEntity<ApiResponse<String>> checkTelephone(@Valid @RequestBody TelephoneDto telephone, HttpServletResponse response) {

        UUID temporaryUserUuid = userService.checkDupleTelephone(telephone.getTelephone());

        Cookie uuidCookie = createCookie("authenicationOnlyTelephone", temporaryUserUuid.toString());
        response.addCookie(uuidCookie);

        // UUID 문자열을 ApiResponse에 포함시켜 반환
        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_TELEPHONE);

        return ResponseEntity.ok(apiResponse); // 여기서 HttpHeaders 객체는 사용하지 않습니다.
    }

    @PostMapping("/signup/email")
    public ResponseEntity<ApiResponse<String>> checkEmail(@Valid @RequestBody EmailDto email, HttpServletResponse response, HttpServletRequest request) {

        // 쿠키에서 temporaryUserUuid 값을 찾음
        Cookie authenicationOnlyTelephoneCookie = getCookieByName(request, "authenicationOnlyTelephone");

        userService.checkDupleEmail(authenicationOnlyTelephoneCookie.getValue(), email.getEmail());

        Cookie uuidCookie = createCookie("temporaryUserUuid", authenicationOnlyTelephoneCookie.getValue());
        response.addCookie(uuidCookie);


        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_USER_CHECK_EMAIL);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/signup/parent")
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request) {

        // 이메일까지 완료된 쿠키인지 확인
        Cookie temporaryUserCookie = getCookieByName(request, "temporaryUserUuid");

        userService.signup(temporaryUserCookie.getValue(), signUpDto);


        ApiResponse<String> apiResponse = new ApiResponse<>(API_SUCCESS_SIGNUP);
        return ResponseEntity.ok(apiResponse);
    }


    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie findCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    findCookie=cookie;
                   return findCookie ;
                }
            }
        }

        throw BusinessException.of(API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND);
    }

    public Cookie createCookie(String name,  String value) {

        Cookie cookie = new Cookie(name, value);
        // 쿠키 속성 설정
        cookie.setHttpOnly(true);  //httponly 옵션 설정
        cookie.setSecure(true); //https 옵션 설정
        cookie.setPath("/"); // 모든 곳에서 쿠키열람이 가능하도록 설정
        cookie.setMaxAge(60 * 60 * 24); //쿠키 만료시간 설정
        
        return cookie;
    }

}
