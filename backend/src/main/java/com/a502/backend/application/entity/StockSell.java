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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "code_id")
	private Code code;

	@Builder
	public StockSell(int price, int cntTotal, Stock stock, User user, Code code) {
		this.price = price;
		this.cntTotal = cntTotal;
		this.cntNot = cntTotal;
		this.stock = stock;
		this.user = user;
		this.code = code;
	}
}
