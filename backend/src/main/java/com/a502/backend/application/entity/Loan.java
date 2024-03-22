package com.a502.backend.application.entity;

import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "loans")
public class Loan extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private int id;

	@Column(name = "loan_uuid")
	private UUID loanUuid;
	@PrePersist
	public void initUUID() {
		if (loanUuid == null)
			loanUuid = UUID.randomUUID();
	}

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

	@Column(name = "payment_now_cnt")
	private int paymentNowCnt;

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

	// L001심사중, L002진행, L003거절 ,L004상환완료
	@ManyToOne
	@JoinColumn(name = "code_id")
	private Code code;

	@Builder
	public Loan(String name, int amount, int paymentDate, String penalty, int paymentTotalCnt, User child, User parent, Code code) {
		this.name = name;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.penalty = penalty;
		this.paymentTotalCnt = paymentTotalCnt;
		this.child = child;
		this.parent = parent;
		this.code = code;
	}
}
