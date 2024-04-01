package com.a502.backend.domain.savings.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsUuidRequest {
	private String savingsUuid;

	@Builder
	public SavingsUuidRequest(String savingsUuid) {
		this.savingsUuid = savingsUuid;
	}
}
