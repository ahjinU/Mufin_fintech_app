package com.a502.backend.application.controller;

import com.a502.backend.application.facade.KeypadFacade;
import com.a502.backend.domain.account.dto.DepositWithdrawalAccountDto;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.account.request.AccounKeypadCreateRequest;
import com.a502.backend.domain.account.request.AccountPasswordRequest;
import com.a502.backend.domain.account.request.AccountValidPasswordRequest;
import com.a502.backend.domain.account.response.AccountValidPasswordResponse;
import com.a502.backend.domain.numberimg.response.KeypadListResponse;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final KeypadFacade keypadFacade;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<KeypadListResponse>> createAccount() {
        KeypadListResponse response = keypadFacade.getKeypadList();
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_GET_KEYPAD, response));
    }

    @PostMapping("/password")
    public ResponseEntity<ApiResponse<Void>> setAccountPassword(@RequestBody AccountPasswordRequest request){
        keypadFacade.craeteAccount(request);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_CREATE));
    }

    @PostMapping("/keypad")
    public ResponseEntity<ApiResponse<KeypadListResponse>> getAccountKeypad(@RequestBody AccounKeypadCreateRequest request ) {
        KeypadListResponse response = keypadFacade.getKeypadList(request);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_GET_KEYPAD, response));
    }

    @PostMapping("/check")
    public ResponseEntity<ApiResponse<AccountValidPasswordResponse>> validAccountPassword(@RequestBody AccountValidPasswordRequest request){
        AccountValidPasswordResponse response = keypadFacade.validAccountPassword(request);
        if (response.getIncorrectCnt() == 0)
            return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_PASSWORD, response));
        else
            return ResponseEntity.status(400).body(new ApiResponse<>(ResponseCode.API_FAIL_ACCOUNT_PASSWORD, response));
    }

}
