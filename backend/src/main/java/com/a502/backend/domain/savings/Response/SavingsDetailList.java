package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsDetailList {
	private LocalDateTime createdAt;
	private LocalDateTime expiredAt;
	private int period;
	private double interest;
	private int paymentAmount;
	private int interestAmount;
	private String state;
	private int balance;

	@Builder
	public SavingsDetailList(LocalDateTime createdAt, LocalDateTime expiredAt, int period, double interest, int paymentAmount, int interestAmount, String state, int balance){
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.period = period;
		this.interest = interest;
		this.paymentAmount = paymentAmount;
		this.interestAmount = interestAmount;
		this.state = state;
		this.balance = balance;
	}
}
