package com.a502.backend.domain.stock.response;

import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class StockOrderList implements Comparable<StockOrderList> {
	private int price;
	private int buyOrderCnt;
	private int sellOrderCnt;

	@Builder
	public StockOrderList(int price, int buyOrderCnt, int sellOrderCnt) {
		this.price = price;
		this.buyOrderCnt = buyOrderCnt;
		this.sellOrderCnt = sellOrderCnt;
	}

	@Override
	public int compareTo(StockOrderList stockOrderList) {
		if (stockOrderList.price < price) {
			return 1;
		} else if (stockOrderList.price > price) {
			return -1;
		}
		return 0;
	}
}
