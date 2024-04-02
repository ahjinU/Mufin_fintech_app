package com.a502.backend.domain.savings.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelSavingsRequest {
	private String accountUuid;
//	private String password;

	@Builder
	public CancelSavingsRequest(String accountUuid
//			, String password
	) {
		this.accountUuid = accountUuid;
//		this.password = password;
	}
}
