package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllowanceByMonthResponse {
	private List<AllowanceListByMonth> dayDetailList;
	private List<Integer> loanDay;
	private List<Integer> savingDay;
	private int incomeMonth;
	private int outcomeMonth;

	@Builder
	public AllowanceByMonthResponse(List<AllowanceListByMonth> dayDetailList, List<Integer> loanDay, List<Integer> savingDay, int incomeMonth, int outcomeMonth) {
		this.loanDay = loanDay;
		this.savingDay = savingDay;
		this.incomeMonth = incomeMonth;
		this.outcomeMonth = outcomeMonth;
	}
}
