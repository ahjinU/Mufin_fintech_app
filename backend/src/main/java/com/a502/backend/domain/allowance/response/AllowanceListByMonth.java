package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllowanceListByMonth {
	private int day;
	private int incomeDay;
	private int outcomeDay;
	private List<OutcomeDetail> details;


	@Builder
	public AllowanceListByMonth(int day, int incomeDay, int outcomeDay, List<OutcomeDetail> details) {
		this.day = day;
		this.incomeDay = incomeDay;
		this.outcomeDay = outcomeDay;
		this.details = details;
	}
}
