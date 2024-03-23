package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyWaitingStockOrderResponse {
	private List<MyWaitingStockOrder> transaction;

	@Builder
	public MyWaitingStockOrderResponse(List<MyWaitingStockOrder> transaction) {
		this.transaction = transaction;
	}

}
