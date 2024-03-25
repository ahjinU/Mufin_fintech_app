package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class StockPriceHistoryByBar {
	private long x;
	private List<Integer> y;

	@Builder
	public StockPriceHistoryByBar(long x, List<Integer> y) {
		this.x = x;
		this.y = y;
	}
}
