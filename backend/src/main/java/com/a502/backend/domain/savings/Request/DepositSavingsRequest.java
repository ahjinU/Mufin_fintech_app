package com.a502.backend.domain.savings.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositSavingsRequest {
	private String password;
	private String accountUuid;
	private int cnt;

	@Builder
	public DepositSavingsRequest(String password, String accountUuid, int cnt) {
		this.password = password;
		this.accountUuid = accountUuid;
		this.cnt = cnt;
	}
}
