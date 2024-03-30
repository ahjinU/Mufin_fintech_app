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

	@Builder
	public StockInfoResponse(int price, double incomeRatio, int transCnt, String imageUrl) {
		this.price = price;
		this.incomeRatio = incomeRatio;
		this.transCnt = transCnt;
		this.imageUrl = imageUrl;
	}
}
