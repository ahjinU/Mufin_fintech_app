package com.a502.backend.application.controller;

import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.account.dto.DepositWithdrawalAccountDto;
import com.a502.backend.domain.stock.response.StockPriceHistoryByBar;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.a502.backend.application.config.constant.JwtConstant.GRANT_TYPE;
import static com.a502.backend.application.config.constant.JwtConstant.HEADER_STRING;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepositWithdrawalAccountDto>> login(@Valid @RequestBody String password) {


        DepositWithdrawalAccountDto account = accountService.createDepositWithdrawalAccount(password);


        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_CREATE));

    }

}
