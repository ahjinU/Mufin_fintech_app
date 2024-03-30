package com.a502.backend.domain.loan.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepayLoanRequest {
	private String loanUuid;
	private int payment_cnt;

	@Builder
	public RepayLoanRequest(String loanUuid, int payment_cnt) {
		this.loanUuid = loanUuid;
		this.payment_cnt = payment_cnt;
	}
}
