package com.a502.backend.domain.payment.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferMoneyRequest {
	private String accountNumberIn;
	private String accountNumberOut;
	private int amount;
	private String transType;

	@Builder
	public TransferMoneyRequest(String accountNumberIn, String accountNumberOut, int amount, String transType) {
		this.accountNumberIn = accountNumberIn;
		this.accountNumberOut = accountNumberOut;
		this.amount = amount;
		this.transType = transType;
	}
}
