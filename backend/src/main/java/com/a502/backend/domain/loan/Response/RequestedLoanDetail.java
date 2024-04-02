package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestedLoanDetail {
	private String childName;
	private String reason;
	private String loanUuid;
	private int amount;
	private int paymentDate;
	private String penalty;
	private int paymentTotalCnt;
	private String[] chatBotConversation;

	@Builder
	public RequestedLoanDetail(String childName, String reason, String loanUuid, int amount, int paymentDate, String penalty, int paymentTotalCnt, String[] chatBotConversation){
		this.childName = childName;
		this.reason = reason;
		this.loanUuid = loanUuid;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.penalty = penalty;
		this.paymentTotalCnt = paymentTotalCnt;
		this.chatBotConversation = chatBotConversation;
	}
}