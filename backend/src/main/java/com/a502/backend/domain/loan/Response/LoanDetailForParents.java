package com.a502.backend.domain.loan.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanDetailForParents {
	private String childName;
	private String reason;
	private int amount;
	private int paymentDate;
	private String penalty;
	private int paymentTotalCnt;
	private int paymentNowCnt;
	private String statusCode;
	private int overdueCnt;
	private LocalDate startDate;
	private LocalDate endDate;

	@Builder
	public LoanDetailForParents(String childName, String reason, int amount, int paymentDate, String penalty, int paymentTotalCnt, int paymentNowCnt, String statusCode, int overdueCnt, LocalDate startDate, LocalDate endDate) {
		this.childName = childName;
		this.reason = reason;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.penalty = penalty;
		this.paymentTotalCnt = paymentTotalCnt;
		this.paymentNowCnt = paymentNowCnt;
		this.statusCode = statusCode;
		this.overdueCnt = overdueCnt;
		this.startDate = startDate;
		this.endDate = endDate;

	}
}
