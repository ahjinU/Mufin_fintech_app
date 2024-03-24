package com.a502.backend.application.controller;

import com.a502.backend.application.facade.PayFacade;
import com.a502.backend.domain.payment.request.MyAccount;
import com.a502.backend.domain.payment.request.RecipientAccount;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PayController {

	private final PayFacade payFacade;

	// 송금 계좌 유효성 체크
	@PostMapping("/transfer")
	public ResponseEntity<ApiResponse<String>> checkRecipient(@RequestBody RecipientAccount recipientAccount) {
		String result = payFacade.checkRecipient(recipientAccount);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_EXIST, result));
	}

	// 출금 계좌 유효성 체크
	@PostMapping("/withdraw")
	public ResponseEntity<ApiResponse<Void>> checkMyAccount(@RequestBody MyAccount myAccount) {
		String accountNumber = myAccount.getAccountNumberOut();
		int amount = myAccount.getAmount();
		payFacade.checkMyAccount(accountNumber, amount);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_ACCOUNT_IS_SUFFICIENT));
	}
}
