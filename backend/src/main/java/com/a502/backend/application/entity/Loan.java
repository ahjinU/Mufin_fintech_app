package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private UUID loanUuid;

	@Column(name = "name")
	private String name;

	@Column(name = "amount")
	private int amount;

	@Column(name = "payment_date")
	private int paymentDate;

	@Column(name = "penalty")
	private String penalty;

	@Column(name = "payment_total_cnt")
	private int paymentTotalCnt;

	@ColumnDefault("0")
	@Column(name = "payment_now_cnt")
	private int paymentNowCnt;

	@Column(name = "status_code")
	private int statusCode;

	@Column(name = "overdue_cnt")
	private int overdueCnt;

	@ManyToOne
	@JoinColumn(name = "child_id")
	private User child;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private User parent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loan_conversation_id")
	private LoanConversation loanConversation;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public Loan(String name, int amount, int paymentDate, String penalty, int paymentTotalCnt, User child, User parent) {
		this.name = name;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.penalty = penalty;
		this.paymentTotalCnt = paymentTotalCnt;
//		this.statusCode = statusCode; // default "심사중"
		this.child = child;
		this.parent = parent;
	}
}
