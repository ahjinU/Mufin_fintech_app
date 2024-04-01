package com.a502.backend.domain.loan.Request;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyLoanRequest {
	private String reason;
	private int amount;
	private int paymentTotalCnt;
	private int paymentDate;
	private String conversation;
	private String penalty;

	@Builder
	public ApplyLoanRequest(String reason, int amount, int paymentTotalCnt, int paymentDate, String conversation, String penalty) {
		this.reason = reason;
		this.amount = amount;
		this.paymentTotalCnt = paymentTotalCnt;
		this.paymentDate = paymentDate;
		this.conversation = conversation;
		this.penalty = penalty;
	}
}
