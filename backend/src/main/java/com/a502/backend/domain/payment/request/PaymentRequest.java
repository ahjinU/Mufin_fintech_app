package com.a502.backend.domain.payment.request;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {
	private int amount;
	private String counterpartyName;

	@Builder
	public PaymentRequest(int amount, String counterpartyName) {
		this.amount = amount;
		this.counterpartyName = counterpartyName;
	}
}
