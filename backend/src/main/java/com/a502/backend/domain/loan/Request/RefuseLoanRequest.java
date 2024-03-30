package com.a502.backend.domain.loan.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefuseLoanRequest {
	private String loanUuid;
	private String reason;

	@Builder
	public RefuseLoanRequest(String loanUuid, String reason) {
		this.loanUuid = loanUuid;
		this.reason = reason;
	}
}
