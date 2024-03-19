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
@Table(name = "stock_details")
public class StockDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_detail_id")
	private int id;

	@Column(name = "stock_detail_uuid")
	private UUID stockDetailUuid;

	@Column(name = "price")
	private int price;

	@Column(name = "highest_price")
	private int highestPrice;

	@Column(name = "lowest_price")
	private int lowestPrice;

	@Column(name = "upper_limit_price")
	private int upperLimitPrice;

	@Column(name = "lower_limit_price")
	private int lowerLimitPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public StockDetail(int price, int highestPrice, int lowestPrice, int upperLimitPrice, int lowerLimitPrice, Stock stock) {
		this.price = price;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.upperLimitPrice = upperLimitPrice;
		this.lowerLimitPrice = lowerLimitPrice;
		this.stock = stock;
	}
}
