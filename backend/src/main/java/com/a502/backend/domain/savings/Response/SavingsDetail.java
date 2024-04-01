package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsDetail {
	private String savingsUuid;
	private double interest;
	private int period;
	private String name;
	private LocalDateTime createdAt;
	@Builder
	public SavingsDetail(String savingsUuid, double interest, int period, String name, LocalDateTime createdAt){
		this.savingsUuid = savingsUuid;
		this.interest = interest;
		this.period = period;
		this.name = name;
		this.createdAt = createdAt;
	}
}
