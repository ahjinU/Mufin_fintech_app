package com.a502.backend.domain.parking.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingDetailList {
	private String transName;
	private int amount;
	private String type;
	private int cnt;
	private int price;
	private double ratio;
	private LocalDate date;

	@Builder
	public ParkingDetailList(String transName, int amount, String type, int cnt, int price, double ratio, LocalDate date){
		this.transName = transName;
		this.amount = amount;
		this.type = type;
		this.cnt = cnt;
		this.price = price;
		this.ratio = ratio;
		this.date = date;
	}
}
