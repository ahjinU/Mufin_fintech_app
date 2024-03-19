package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
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
public class LoanRefusal extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_refusal_id")
	private int id;

	@Column(name = "loan_refusal_uuid")
	private UUID loanRefusalUuid;
	@PrePersist
	public void initUUID() {
		if (loanRefusalUuid == null)
			loanRefusalUuid = UUID.randomUUID();
	}

	@Column(name = "reason")
	private String reason;

	@OneToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@Builder
	public LoanRefusal(String reason, Loan loan) {
		this.reason = reason;
		this.loan = loan;
	}
}
