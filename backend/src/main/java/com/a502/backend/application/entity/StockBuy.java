package com.a502.backend.application.entity;

import com.a502.backend.global.code.StockCode;
import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_buys")
public class StockBuy extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_buy_id")
	private int id;

	@Column(name = "stock_buy_uuid")
	@UuidGenerator
	private UUID stockBuyUuid;
	@PrePersist
	public void initUUID() {
		if (stockBuyUuid == null)
			stockBuyUuid = UUID.randomUUID();
	}

	@Column(name = "price")
	private int price;

	@Column(name = "cnt_total")
	private int cntTotal;

	@Setter
	@Column(name = "cnt_not")
	private int cntNot;

	@Column(name = "status")
	private int status;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public StockBuy(int price, int cntTotal, Stock stock, User user) {
		this.price = price;
		this.cntTotal = cntTotal;
		this.cntNot = cntTotal;
		this.status = StockCode.STOCK_STATUS_NEW;
		this.stock = stock;
		this.user = user;
	}

}
