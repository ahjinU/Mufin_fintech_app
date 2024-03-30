package com.a502.backend.domain.stock.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalStockListResponse {
	private List<TotalStockList> stock;

	@Builder
	public TotalStockListResponse(List<TotalStockList> stock) {
		this.stock = stock;
	}
}

