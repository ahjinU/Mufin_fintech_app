package com.a502.backend.domain.savings.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterSavingsRequest {
	private String name;
	private double interest;
	private int period;

	@Builder
	public RegisterSavingsRequest(String name, double interest, int period) {
		this.name = name;
		this.interest = interest;
		this.period = period;
	}
}
