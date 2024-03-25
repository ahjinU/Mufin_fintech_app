package com.a502.backend.application.entity;

import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
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
	private String accountNumber; //계좌번호
	//입출금 계좌: 5021-@@@@-@@-@@@@
	//적금 계좌: 5022-@@@@-@@-@@@@

	@Column(name = "balance")
	private int balance; //잔고 0으로 셋팅

	@Column(name = "interestAmount")
	private int interestAmount; //(입출금) 0, (적금)그때 오는 이자율로

	@Column(name = "payment_amount") //(적금통장) 초기에 0으로 셋
	private int paymentAmount; //월 납입액

	@Column(name = "payment_date")
	private int paymentDate; //월 납입일

	@Column(name = "payment_cycle") //0으로 셋
	private int paymentCycle; //납입회차

	@Column(name = "password")
	private String password; //비밀번호

	@Column(name = "incorrect_cnt")
	private int incorrectCount; //비밀번호 틀린 횟수 초기 생성시에는 0으로 셋

	@ManyToOne
	@JoinColumn(name = "saving_id")
	private Savings savings; // 적금 상품

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 계좌 주인

	@ManyToOne
	@JoinColumn(name = "status_code_id")
	private Code statusCode; //001~004 (만기, 정상, 해지, 정지)로 초기 생성시에는 정상으로 셋

	@ManyToOne
	@JoinColumn(name = "type_code_id")
	private Code typeCode; // 입출금, 적금으로 요청시 오는 코드로 셋

	@Builder
	public Account(String accountNumber, int balance, int interestAmount, int paymentAmount, int paymentDate, int paymentCycle, String password, int incorrectCount, User user, Code statusCode, Code typeCode) {
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
