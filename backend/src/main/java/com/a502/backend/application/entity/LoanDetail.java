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
@Table(name = "loan_details")
public class LoanDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_detail_id")
	private int id;

	@Column(name = "loan_detail_uuid")
	private UUID loanDetailUuid;
	@PrePersist
	public void initUUID() {
		if (loanDetailUuid == null)
			loanDetailUuid = UUID.randomUUID();
	}

	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_datail_id")
	private AccountDetail accountDetail;

	@Builder
	public LoanDetail(Loan loan, AccountDetail accountDetail) {
		this.loan = loan;
		this.accountDetail = accountDetail;
	}
}
