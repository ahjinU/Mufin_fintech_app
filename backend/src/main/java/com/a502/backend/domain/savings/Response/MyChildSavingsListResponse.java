package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyChildSavingsListResponse {
	List<SavingsDetailAboutChild> savingsDetailListByChild;

	@Builder
	public MyChildSavingsListResponse(List<SavingsDetailAboutChild> savingsDetailListByChild) {
		this.savingsDetailListByChild = savingsDetailListByChild;
	}
}
