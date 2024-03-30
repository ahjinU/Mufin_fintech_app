package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanListForChildResponse {
	private List<LoanList> loansList;

	@Builder
	public LoanListForChildResponse(List<LoanList> loansList) {
		this.loansList = loansList;
	}

}
