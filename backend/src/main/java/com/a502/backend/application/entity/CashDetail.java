package com.a502.backend.application.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
	private UUID cashDetailUuid; // Assuming binary UUID storage, adjust if using a different type

	@Column(name = "type")
	private String type;

	@Column(name = "amount")
	private int amount;

	@ManyToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public CashDetail(String type, int amount, User user) {
		this.type = type;
		this.amount = amount;
		this.user = user;
	}
}
