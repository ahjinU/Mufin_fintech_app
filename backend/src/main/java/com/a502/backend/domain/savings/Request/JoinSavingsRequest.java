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
	private String password;

	@Builder
	public JoinSavingsRequest(String savingsUuid, int paymentAmount, int paymentDate, String password) {
		this.savingsUuid = savingsUuid;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.password = password;
	}
}
