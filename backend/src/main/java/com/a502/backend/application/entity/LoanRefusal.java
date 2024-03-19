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
@Table(name = "loan_refusals")
public class LoanRefusal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_refusal_id")
	private int id;

	@Column(name = "loan_refusal_uuid")
	private UUID loanRefusalUuid;

	@Column(name = "reason")
	private String reason;

	@OneToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Builder
	public LoanRefusal(String reason, Loan loan) {
		this.reason = reason;
		this.loan = loan;
	}
}
