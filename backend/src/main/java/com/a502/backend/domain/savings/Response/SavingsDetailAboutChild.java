package com.a502.backend.domain.savings.Response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsDetailAboutChild {
	private String userName;
	private List<SavingsDetailList> savingsDetailList;


	@Builder
	public SavingsDetailAboutChild(String userName, List<SavingsDetailList> savingsDetailList) {
		this.userName = userName;
		this.savingsDetailList = savingsDetailList;
	}
}
