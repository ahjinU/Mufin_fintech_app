package com.a502.backend.application.entity;


import com.a502.backend.global.entity.BaseEntity;
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
public class CashDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cash_detail_id")
	private int id;

	@Column(name = "trans_at")
	private LocalDateTime transAt;

	@Column(name = "cash_detail_uuid")
	private UUID cashDetailUuid; // Assuming binary UUID storage, adjust if using a different type
	@PrePersist
	public void initUUID() {
		if (cashDetailUuid == null)
			cashDetailUuid = UUID.randomUUID();
	}

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

	@Builder
	public CashDetail(String type, int amount, User user) {
		this.type = type;
		this.amount = amount;
		this.user = user;
	}
}
