package com.a502.backend.application.entity;

import com.a502.backend.global.code.StockCode;
import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_sells")
public class StockSell extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_sell_id")
	private int id;

	@Column(name = "stock_sell_uuid")
	private UUID stockSellUuid;
	@PrePersist
	public void initUUID() {
		if (stockSellUuid == null)
			stockSellUuid = UUID.randomUUID();
	}

	@Column(name = "price")
	private int price;

	@Column(name = "cnt_total")
	private int cntTotal;

	@Setter
	@Column(name = "cnt_not")
	private int cntNot;

	@Setter
	@Column(name = "status")
	private int status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public StockSell(int price, int cntTotal, Stock stock, User user) {
		this.price = price;
		this.cntTotal = cntTotal;
		this.cntNot = cntTotal;
		this.status = StockCode.STOCK_STATUS_TRANS;
		this.stock = stock;
		this.user = user;
	}
}
