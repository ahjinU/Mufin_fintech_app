package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalStockList {
	private String name;
	private int price;
	private double incomeRatio;
	private int transCnt;
	private String imageUrl;

	@Builder
	public TotalStockList(String name, int price, double incomeRatio, int transCnt, String imageUrl) {
		this.name = name;
		this.price = price;
		this.incomeRatio = incomeRatio;
		this.transCnt = transCnt;
		this.imageUrl = imageUrl;
	}
}
