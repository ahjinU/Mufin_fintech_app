package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyStockListResponse {
	private List<MyStockList> myStockList;
	private int totalIncome;
	private int totalPrice;

	@Builder
	public MyStockListResponse(List<MyStockList> myStockList, int totalIncome, int totalPrice) {
		this.myStockList = myStockList;
		this.totalIncome = totalIncome;
		this.totalPrice = totalPrice;
	}
}
