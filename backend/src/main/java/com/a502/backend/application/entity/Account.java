package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int id;

	@Column(name = "account_uuid")
	private byte[] accountUuid;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "balance")
	private int balance;

	@Column(name = "state")
	private String state;

	@Column(name = "interestAmount")
	private int interestAmount; //이자수령액

	@Column(name = "type")
	private String type;

	@Column(name = "payment_amount")
	private int paymentAmount;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "payment_cycle")
	private int paymentCycle;

	@Column(name = "password")
	private int password;

	@Column(name = "incorrect_cnt")
	private int incorrectCount;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "saving_id")
	private Savings savings;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Account(int id, byte[] accountUuid, String accountNumber, int balance, String state, int interestAmount, String type, int paymentAmount, LocalDateTime paymentDate, int paymentCycle, int password, int incorrectCount, Boolean isDeleted, Savings savings, User user) {
		this.id = id;
		this.accountUuid = accountUuid;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.state = state;
		this.interestAmount = interestAmount;
		this.type = type;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentCycle = paymentCycle;
		this.password = password;
		this.incorrectCount = incorrectCount;
		this.isDeleted = isDeleted;
		this.savings = savings;
		this.user = user;
	}
}
