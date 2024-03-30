package com.a502.backend.domain.allowance.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OutcomeDetail {
	private String storeName;
	private String type;
	private String accountDetailUuid;
	private int amount;
	private String category;

	@Builder
	public OutcomeDetail(String storeName, String type, String accountDetailUuid, int amount, String category) {
		this.storeName = storeName;
		this.type = type;
		this.accountDetailUuid = accountDetailUuid;
		this.amount = amount;
		this.category = category;
	}
}
