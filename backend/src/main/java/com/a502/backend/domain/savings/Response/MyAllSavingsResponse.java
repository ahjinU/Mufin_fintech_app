package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyAllSavingsResponse {
	List<MySavings> savingsList;

	@Builder
	public MyAllSavingsResponse(List<MySavings> savingsList) {
		this.savingsList = savingsList;
	}
}
