package com.a502.backend.domain.savings.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinSavingsRequest {
	private String savingsUuid;
	private int paymentAmount;
	private int paymentDate;

	@Builder
	public JoinSavingsRequest(String savingsUuid, int paymentAmount, int paymentDate) {
		this.savingsUuid = savingsUuid;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
	}
}
