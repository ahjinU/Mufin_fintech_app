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
@Table(name = "loan_details")
public class LoanDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_detail_id")
	private int id;

	@Column(name = "loan_detail_uuid")
	private byte[] loanDetailUuid;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_datail_id")
	private AccountDetail accountDetail;

	@Builder
	public LoanDetail(int id, byte[] loanDetailUuid, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Loan loan, AccountDetail accountDetail) {
		this.id = id;
		this.loanDetailUuid = loanDetailUuid;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.loan = loan;
		this.accountDetail = accountDetail;
	}
}
