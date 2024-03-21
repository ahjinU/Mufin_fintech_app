package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceAndStockOrderList {
	private int price;
	private List<StockOrderList> stockOrderList;

	@Builder
	public PriceAndStockOrderList(int price, List<StockOrderList> stockOrderList) {
		this.price = price;
		this.stockOrderList = stockOrderList;
	}

}
