package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanList {
	private String reason;
	private String loanUuid;
	private int amount;
	private int paymentTotalCnt;
	private int paymentNowCnt;
	private int remainderAmount;
	private String status;
	private int overDueCnt;
	private String loanRefusalReason;

	@Builder
	public LoanList(String reason, String loanUuid, int amount, int paymentTotalCnt, int paymentNowCnt, String status, int overDueCnt, String loanRefusalReason, int remainderAmount) {
		this.reason = reason;
		this.loanUuid = loanUuid;
		this.amount = amount;
		this.paymentTotalCnt = paymentTotalCnt;
		this.paymentNowCnt = paymentNowCnt;
		this.remainderAmount = remainderAmount;
		this.status = status;
		this.overDueCnt = overDueCnt;
		this.loanRefusalReason = loanRefusalReason;
	}

	public void updateLoanDetail(String loanRefusalReason) {
		this.loanRefusalReason = loanRefusalReason;
	}
}
