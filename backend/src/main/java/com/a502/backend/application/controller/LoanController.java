package com.a502.backend.application.controller;

import com.a502.backend.application.facade.LoanFacade;
import com.a502.backend.domain.loan.Request.ApplyLoanRequest;
import com.a502.backend.domain.loan.Request.LoanUuidRequest;
import com.a502.backend.domain.loan.Request.RepayLoanRequest;
import com.a502.backend.domain.loan.Response.LoanDetailResponse;
import com.a502.backend.domain.loan.Response.LoanListForChildResponse;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {
	private final LoanFacade loanFacade;

	// 대출 신청
	@PostMapping("/apply")
	public ResponseEntity<ApiResponse<Void>> applyLoan(@RequestBody ApplyLoanRequest applyLoanRequest) {
		loanFacade.applyLoan(applyLoanRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_LOAN_APPLY));
	}

	@PostMapping("/total/child")
	public ResponseEntity<ApiResponse<LoanListForChildResponse>> getAllLoansForChild() {
		LoanListForChildResponse result = loanFacade.getAllLoansForChild();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_LOAN_GET_ALL_FOR_CHILD, result));
	}

	@PostMapping("/detail/child")
	public ResponseEntity<ApiResponse<LoanDetailResponse>> getLoanDetailForChild(@RequestBody LoanUuidRequest loanUuidRequest) {
		LoanDetailResponse result = loanFacade.getLoanDetailForChild(loanUuidRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_LOAN_GET_DETAIL_FOR_CHILD, result));
	}

	@PostMapping("/repay")
	public ResponseEntity<ApiResponse<Void>> repayLoan(@RequestBody RepayLoanRequest repayLoanRequest){
		loanFacade.repayLoan(repayLoanRequest);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_LOAN_REPAY));
	}

}
