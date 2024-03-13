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
@Table(name = "stock_buys")
public class StockBuy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sotck_buy_id")
	private int id;

	@Column(name = "stock_buy_uuid")
	private byte[] stockBuyUuid;

	@Column()
	private int price;

	@Column(name = "cnt_total")
	private int cntTotal;

	@Column(name = "cnt_not")
	private int cntNot;

	@Column()
	private int status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public StockBuy(int id, byte[] stockBuyUuid, int price, int cntTotal, int cntNot, int status, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Stock stock, User user) {
		this.id = id;
		this.stockBuyUuid = stockBuyUuid;
		this.price = price;
		this.cntTotal = cntTotal;
		this.cntNot = cntNot;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.stock = stock;
		this.user = user;
	}

}
