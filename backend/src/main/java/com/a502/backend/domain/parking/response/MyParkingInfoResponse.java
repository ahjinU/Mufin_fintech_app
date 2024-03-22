package com.a502.backend.domain.parking.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyParkingInfoResponse {
	private int balanceToday;
	private int balanceTmrw;
	private double ratio;
	private int interest;

	@Builder
	public MyParkingInfoResponse(int balanceToday, int balanceTmrw, double ratio, int interest) {
		this.balanceToday = balanceToday;
		this.balanceTmrw = balanceTmrw;
		this.ratio = ratio;
		this.interest = interest;
	}
}
