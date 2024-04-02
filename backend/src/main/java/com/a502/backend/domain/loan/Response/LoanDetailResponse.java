package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanDetailResponse {
	private String reason;
	private int totalAmount;
	private int remainderAmount;
	private LocalDate startDate;
	private String remainderDay;
	private int paymentDate;
	private int paymentAmount;
	private int overDueCnt;

	@Builder
	public LoanDetailResponse(String reason, int totalAmount, int remainderAmount, LocalDate startDate, String remainderDay, int paymentDate, int overDueCnt, int paymentAmount) {
		this.reason = reason;
		this.totalAmount = totalAmount;
		this.remainderAmount = remainderAmount;
		this.startDate = startDate;
		this.remainderDay = remainderDay;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.overDueCnt = overDueCnt;
	}
}
