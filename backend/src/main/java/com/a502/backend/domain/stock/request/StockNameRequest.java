package com.a502.backend.domain.stock.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockNameRequest {
	private String name;

	@Builder
	public StockNameRequest(String name) {
		this.name = name;
	}
}
