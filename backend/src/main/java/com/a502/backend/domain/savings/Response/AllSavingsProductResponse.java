package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllSavingsProductResponse {
	List<SavingsDetail> savingsList;

	@Builder
	public AllSavingsProductResponse(List<SavingsDetail> savingsList) {
		this.savingsList = savingsList;
	}
}
