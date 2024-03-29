package com.a502.backend.domain.savings.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MySavings {
	private String accountNumber;
	private String accountUuid;
	private int balance;
	private String state;
	private int paymentAmount;
	private int paymentDate;
	private int paymentCycle;
	private LocalDateTime createdAt;
	private double savingsInterest;
	private int savingsPeriod;
	private String savingsName;
	private int overdueCnt;
	private LocalDateTime expiredAt;

	@Builder
	public MySavings(String accountNumber, String accountUuid, int balance, String state, int paymentAmount, int paymentDate, int paymentCycle, LocalDateTime createdAt, double savingsInterest, int savingsPeriod, String savingsName, int overdueCnt, LocalDateTime expiredAt) {
		this.accountNumber = accountNumber;
		this.accountUuid = accountUuid;
		this.balance = balance;
		this.state = state;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentCycle = paymentCycle;
		this.createdAt = createdAt;
		this.savingsInterest = savingsInterest;
		this.savingsPeriod = savingsPeriod;
		this.savingsName = savingsName;
		this.overdueCnt = overdueCnt;
		this.expiredAt = expiredAt;
	}
}
