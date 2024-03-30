package com.a502.backend.domain.loan.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanUuidRequest {
	private String loanUuid;

	@Builder
	public LoanUuidRequest(String loanUuid){
		this.loanUuid = loanUuid;
	}
}
