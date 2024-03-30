package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestedLoanDetailForParentsResponse {
	private List<RequestedLoanDetail> loansList;

	@Builder
	public RequestedLoanDetailForParentsResponse(List<RequestedLoanDetail> loansList){
		this.loansList = loansList;
	}
}
