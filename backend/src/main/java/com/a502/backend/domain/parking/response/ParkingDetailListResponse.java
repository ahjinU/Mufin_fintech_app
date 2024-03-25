package com.a502.backend.domain.parking.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingDetailListResponse {
	private List<ParkingDetailList> transaction;

	@Builder
	public ParkingDetailListResponse(List<ParkingDetailList> transaction) {
		this.transaction = transaction;
	}
}
