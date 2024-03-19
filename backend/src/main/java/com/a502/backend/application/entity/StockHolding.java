package com.a502.backend.application.entity;

import com.a502.backend.domain.stock.StockHoldingsId;
import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_holdings")
public class StockHolding extends BaseEntity {

	@EmbeddedId
	private StockHoldingsId id;

	@Column(name = "stock_holding_uuid")
	private UUID stockHoldingUuid;
	@PrePersist
	public void initUUID() {
		if (stockHoldingUuid == null)
			stockHoldingUuid = UUID.randomUUID();
	}

	@Setter
	@Column(name = "cnt")
	private int cnt;

	@Setter
	@Column(name = "total")
	private int total;

	@MapsId("user")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@MapsId("stock")
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@Builder
	public StockHolding(int cnt, int total, Stock stock, User user) {
		this.cnt = cnt;
		this.total = total;
		this.id = StockHoldingsId.builder().
				user(user).stock(stock).build();
	}
}
