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
@Table(name = "stock_holdings")
public class StockHolding {
	@Column(name = "stock_holding_uuid")
	private byte[] stockHoldingUuid;

	@Column()
	private int cnt;

	@Column()
	private int total;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Id
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public StockHolding(byte[] stockHoldingUuid, int cnt, int total, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Stock stock, User user) {
		this.stockHoldingUuid = stockHoldingUuid;
		this.cnt = cnt;
		this.total = total;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.stock = stock;
		this.user = user;
	}
}
