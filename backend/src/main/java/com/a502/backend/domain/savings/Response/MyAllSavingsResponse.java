package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyAllSavingsResponse {
	private int savingsTotalBalance;
	List<MySavings> savingsList;

	@Builder
	public MyAllSavingsResponse(int savingsTotalBalance, List<MySavings> savingsList) {
		this.savingsTotalBalance =  savingsTotalBalance;
		this.savingsList = savingsList;
	}
}
