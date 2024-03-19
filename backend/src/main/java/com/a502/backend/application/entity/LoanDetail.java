package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "loan_details")
public class LoanDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_detail_id")
	private int id;

	@Column(name = "loan_detail_uuid")
	private UUID loanDetailUuid;

	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_datail_id")
	private AccountDetail accountDetail;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public LoanDetail(Loan loan, AccountDetail accountDetail) {
		this.loan = loan;
		this.accountDetail = accountDetail;
	}
}
