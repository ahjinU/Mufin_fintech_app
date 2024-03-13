package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "loans")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private int id;

	@Column(name = "loan_uuid")
	private byte[] loanUuid;

	@Column()
	private String name;

	@Column()
	private int amount;

	@Column(name = "payment_date")
	private int paymentDate;

	@Column()
	private String penalty;

	@Column(name = "payment_total_cnt")
	private int paymentTotalCnt;

	@Column(name = "payment_now_cnt")
	private int paymentNowCnt;

	@Column(name = "status_code")
	private int statusCode;

	@Column(name = "overdue_cnt")
	private int overdueCnt;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public Loan(int id, byte[] loanUuid, String name, int amount, int paymentDate, String penalty, int paymentTotalCnt, int paymentNowCnt, int statusCode, int overdueCnt, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted) {
		this.id = id;
		this.loanUuid = loanUuid;
		this.name = name;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.penalty = penalty;
		this.paymentTotalCnt = paymentTotalCnt;
		this.paymentNowCnt = paymentNowCnt;
		this.statusCode = statusCode;
		this.overdueCnt = overdueCnt;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
	}
}
