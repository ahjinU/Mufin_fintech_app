package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accounts")
public class Account extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int id;

	@Column(name = "account_uuid")
	private UUID accountUuid;
	@PrePersist
	public void initUUID() {
		if (accountUuid == null)
			accountUuid = UUID.randomUUID();
	}

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "balance")
	private int balance;

	@Column(name = "interestAmount")
	private int interestAmount; //이자수령액

	@Column(name = "payment_amount")
	private int paymentAmount;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "payment_cycle")
	private int paymentCycle;

	@Column(name = "password")
	private String password;

	@Column(name = "incorrect_cnt")
	private int incorrectCount;

	@ManyToOne
	@JoinColumn(name = "saving_id")
	private Savings savings;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "status_code_id")
	private Code statusCode;

	@ManyToOne
	@JoinColumn(name = "type_code_id")
	private Code typeCode;

	@Builder
	public Account(String accountNumber, int balance, int interestAmount, int paymentAmount, LocalDateTime paymentDate, int paymentCycle, String password, int incorrectCount, User user, Code statusCode, Code typeCode) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestAmount = interestAmount;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentCycle = paymentCycle;
		this.password = password;
		this.incorrectCount = incorrectCount;
		this.user = user;
		this.statusCode = statusCode;
		this.typeCode = typeCode;
	}
}
