package com.a502.backend.application.entity;

import com.a502.backend.global.code.StockCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_sells")
public class StockSell {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_sell_id")
	private int id;

	@Column(name = "stock_sell_uuid")
	private UUID stockSellUuid;

	@Column(name = "price")
	private int price;

	@Column(name = "cnt_total")
	private int cntTotal;

	@Column(name = "cnt_not")
	private int cntNot;

	@Column()
	private int status;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@ColumnDefault("false")
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Builder
	public StockSell(int price, int cntTotal, Stock stock, User user) {
		this.price = price;
		this.cntTotal = cntTotal;
		this.cntNot = cntTotal;
		this.status = StockCode.STOCK_STATUS_NEW;
		this.stock = stock;
		this.user = user;
	}
}
