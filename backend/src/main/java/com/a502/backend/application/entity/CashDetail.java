package com.a502.backend.application.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cash_details")
public class CashDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cash_detail_id")
	private int id;

	@Column(name = "cash_detail_uuid")
	private byte[] cashDetailUuid; // Assuming binary UUID storage, adjust if using a different type

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@Builder

	public CashDetail(int id, byte[] cashDetailUuid, LocalDateTime createdAt, String type, Memo memo, Receipt receipt, boolean isDeleted, Account account) {
		this.id = id;
		this.cashDetailUuid = cashDetailUuid;
		this.createdAt = createdAt;
		this.type = type;
		this.memo = memo;
		this.receipt = receipt;
		this.isDeleted = isDeleted;
		this.account = account;
	}
}
