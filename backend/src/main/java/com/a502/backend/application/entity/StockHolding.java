package com.a502.backend.application.entity;

import com.a502.backend.domain.stock.StockHoldingsId;
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
@Table(name = "stock_holdings")
public class StockHolding {

	@EmbeddedId
	private StockHoldingsId id;

	@Column(name = "stock_holding_uuid")
	private UUID stockHoldingUuid;

	@Column(name = "cnt")
	private int cnt;

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

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@ColumnDefault("false")
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Builder
	public StockHolding(int cnt, int total, Stock stock, User user) {
		this.cnt = cnt;
		this.total = total;
		this.id = StockHoldingsId.builder().
				user(user).stock(stock).build();
	}
}
