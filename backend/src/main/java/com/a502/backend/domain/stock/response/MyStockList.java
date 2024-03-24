package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyStockList {
	private String name;
	private int cnt;
	private int income;
	private double incomeRatio;
	private int priceAvg;
	private int priceCur;
	private int totalPriceAvg;
	private int totalPriceCur;

	@Builder
	public MyStockList(String name, int cnt, int income, double incomeRatio, int priceAvg, int priceCur, int totalPriceAvg, int totalPriceCur) {
		this.name = name;
		this.cnt = cnt;
		this.income = income;
		this.incomeRatio = incomeRatio;
		this.priceAvg = priceAvg;
		this.priceCur = priceCur;
		this.totalPriceAvg = totalPriceAvg;
		this.totalPriceCur = totalPriceCur;
	}
}
