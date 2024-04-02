package com.a502.backend.application.controller;

import com.a502.backend.application.facade.SavingFacade;
import com.a502.backend.domain.savings.Request.*;
import com.a502.backend.domain.savings.Response.*;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savings")
@RequiredArgsConstructor
public class SavingsController {
	private final SavingFacade savingFacade;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Void>> registerSavings(@RequestBody RegisterSavingsRequest registerSavingsRequest) {
		savingFacade.registerSavings(registerSavingsRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_REGISTER));
	}

	@PostMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> deleteSavings(@RequestBody SavingsUuidRequest savingsUuidRequest) {
		savingFacade.deleteSavings(savingsUuidRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_DELETE));
	}

	@PostMapping("/search/total")
	public ResponseEntity<ApiResponse<AllSavingsProductResponse>> getAllSavingsProduct() {
		AllSavingsProductResponse result = savingFacade.getAllSavingProduct();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_GET_ALL, result));
	}

	@PostMapping("/search/detail")
	public ResponseEntity<ApiResponse<SavingsDetail>> getSavingsProduct(@RequestBody SavingsUuidRequest savingsUuidRequest){
		SavingsDetail result = savingFacade.getSavingsProduct(savingsUuidRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_GET_DETAIL, result));
	}

	@PostMapping("/join")
	public ResponseEntity<ApiResponse<Void>> joinSavings(@RequestBody JoinSavingsRequest joinSavingsRequest) {
		savingFacade.joinSavings(joinSavingsRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_JOIN));
	}

	@PostMapping("/search/mychild")
	public ResponseEntity<ApiResponse<MyChildSavingsListResponse>> getMyChildSavings() {
		MyChildSavingsListResponse result = savingFacade.getMyChildSavings();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_GET_MY_CHILD, result));
	}

	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse<Void>> depositToSavings(@RequestBody DepositSavingsRequest depositSavingsRequest) {
		savingFacade.depositToSavings(depositSavingsRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_DEPOSIT));
	}

	@PostMapping("/cancel")
	public ResponseEntity<ApiResponse<Void>> cancelSavings(@RequestBody CancelSavingsRequest cancelSavingsRequest) {
		savingFacade.cancelSavings(cancelSavingsRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_CANCEL));
	}

	@PostMapping("/terminate")
	public ResponseEntity<ApiResponse<Void>> terminateSavings(@RequestBody CancelSavingsRequest cancelSavingsRequest) {
		savingFacade.terminateSavings(cancelSavingsRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_TERMINATE));
	}

	@PostMapping("/search/mine/total")
	public ResponseEntity<ApiResponse<MyAllSavingsResponse>> getMyAllSavings() {
		MyAllSavingsResponse result = savingFacade.getMyAllSavings();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_GET_ALL_MINE, result));
	}

	@PostMapping("/search/mine/detail")
	public ResponseEntity<ApiResponse<MySavings>> getMySavingsDetail(@RequestBody CancelSavingsRequest accountUUidRequest) {
		MySavings result = savingFacade.getMySavingsDetail(accountUUidRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_SAVINGS_GET_DETAIL_MINE, result));
	}
}
