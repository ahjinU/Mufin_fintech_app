package com.a502.backend.domain.stock.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockPriceHistoryRequest {
	private String name;
	private int period;

	@Builder
	public StockPriceHistoryRequest(String name, int period) {
		this.name = name;
		this.period = period;
	}
}
