package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyWaitingStockOrder {
	private String transName;
	private int amount;
	private String type;
	private int cnt;
	private int price;

	@Builder
	public MyWaitingStockOrder(String transName, int amount, String type, int cnt, int price) {
		this.transName = transName;
		this.amount = amount;
		this.type = type;
		this.cnt = cnt;
		this.price = price;
	}
}
