package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class StockPriceHistoryByBar {
	private LocalDate createdAt;
	private int price;
	private int highestPrice;
	private int lowestPrice;
	private int startPrice;

	@Builder
	public StockPriceHistoryByBar(LocalDate createdAt, int price, int highestPrice, int lowestPrice, int startPrice) {
		this.createdAt = createdAt;
		this.price = price;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.startPrice = startPrice;
	}
}
