package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StockPriceHistoryByLine {
	private LocalDate date;
	private int price;

	@Builder
	public StockPriceHistoryByLine(LocalDate date, int price){
		this.date = date;
		this.price = price;
	}
}
