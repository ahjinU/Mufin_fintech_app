package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanListForChildResponse {
	private int totalRemainderAmount;
	private List<LoanList> loansList;

	@Builder
	public LoanListForChildResponse(int totalRemainderAmount, List<LoanList> loansList) {
		this.totalRemainderAmount = totalRemainderAmount;
		this.loansList = loansList;
	}

}
