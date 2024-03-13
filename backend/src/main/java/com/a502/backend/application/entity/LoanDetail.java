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

	@Builder
	public LoanDetail(int id, byte[] loanDetailUuid, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted) {
		this.id = id;
		this.loanDetailUuid = loanDetailUuid;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
	}
}
