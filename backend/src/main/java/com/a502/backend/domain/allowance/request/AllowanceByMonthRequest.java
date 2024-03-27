package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllowanceByMonthRequest {
	private int year;
	private int month;
	private String childUuid;

	@Builder
	public AllowanceByMonthRequest(int year, int month, String childUuid) {
		this.year = year;
		this.month = month;
		this.childUuid = childUuid;
	}
}
