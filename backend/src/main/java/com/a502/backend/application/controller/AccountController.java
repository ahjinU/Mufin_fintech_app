package com.a502.backend.application.controller;

import com.a502.backend.domain.account.dto.DepositWithdrawalAccountDto;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepositWithdrawalAccountDto>> login(@Valid @RequestBody String password) {

        System.out.println("[AccountController]: create");

        accountService.createDepositWithdrawalAccount(password);

        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_CREATE));
    }

}
