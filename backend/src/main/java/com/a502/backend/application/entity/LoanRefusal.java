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
@Table(name = "loan_refusals")
public class LoanRefusal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_refusal_id")
	private int id;

	@Column(name = "loan_refusal_uuid")
	private byte[] loanRefusalUuid;

	@Column()
	private String reason;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToOne()
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@Builder
	public LoanRefusal(int id, byte[] loanRefusalUuid, String reason, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Loan loan) {
		this.id = id;
		this.loanRefusalUuid = loanRefusalUuid;
		this.reason = reason;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.loan = loan;
	}
}
