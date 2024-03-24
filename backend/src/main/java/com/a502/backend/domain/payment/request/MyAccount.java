package com.a502.backend.domain.payment.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyAccount {
	private String accountNumberOut;
	private int amount;

	@Builder
	public MyAccount(String accountNumberOut, int amount) {
		this.accountNumberOut = accountNumberOut;
		this.amount = amount;
	}
}
