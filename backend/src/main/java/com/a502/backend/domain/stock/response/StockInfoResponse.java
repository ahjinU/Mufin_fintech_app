package com.a502.backend.domain.stock.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockInfoResponse {
	private int price;
	private double incomeRatio;
	private int transCnt;
	private String imageUrl;
	private int upperLimitPrice;
	private int lowerLimitPrice;

	@Builder
	public StockInfoResponse(int price, double incomeRatio, int transCnt, String imageUrl, int upperLimitPrice, int lowerLimitPrice) {
		this.price = price;
		this.incomeRatio = incomeRatio;
		this.transCnt = transCnt;
		this.imageUrl = imageUrl;
		this.upperLimitPrice = upperLimitPrice;
		this.lowerLimitPrice = lowerLimitPrice;
	}
}
