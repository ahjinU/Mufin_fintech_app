package com.a502.backend.domain.payment.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipientAccount {
	private String accountNumberIn;

	@Builder
	public RecipientAccount(String accountNumberIn) {
		this.accountNumberIn = accountNumberIn;
	}
}
