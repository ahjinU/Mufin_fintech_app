package com.a502.backend.application.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access =AccessLevel.PUBLIC)
public class StockOrderList {
	private int price;
	private int buyOrderCnt;
	private int sellOrderCnt;

	@Builder
	public StockOrderList(int price, int buyOrderCnt, int sellOrderCnt){
		this.price = price;
		this.buyOrderCnt = buyOrderCnt;
		this.sellOrderCnt = sellOrderCnt;
	}
}
