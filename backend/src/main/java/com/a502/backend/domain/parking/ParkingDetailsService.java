package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.response.ParkingDetailListResponse;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParkingDetailsService {
	private final ParkingDetailsRepository parkingDetailsRepository;

	@Transactional
	public ParkingDetail getLastDetail(Parking parking) {
		return parkingDetailsRepository.findTopByParkingOrderByCreatedAtDesc(parking);
	}

	@Transactional
	public ParkingDetail saveStockBuy(StockBuy stockBuy, Parking parking, int cnt, Code code) {
		ParkingDetail last = getLastDetail(parking);
		// 거래 주식회사 이름
		String counterpartyName = stockBuy.getStock().getName();
		int amount = -stockBuy.getPrice() * cnt;
		return parkingDetailsRepository.save(ParkingDetail.builder()
				.parking(parking)
				.amount(amount)
				.balance(last.getBalance() + amount)
				.counterpartyName(counterpartyName)
				.code(code)
				.cnt(cnt)
				.build()
		);
	}

	@Transactional
	public ParkingDetail saveStockSell(StockSell stockSell, Parking parking, int cnt, Code code) {
		ParkingDetail last = getLastDetail(parking);
		// 거래 주식회사 이름
		String counterpartyName = stockSell.getStock().getName();
		int amount = stockSell.getPrice() * cnt;
		return parkingDetailsRepository.save(ParkingDetail.builder()
				.parking(parking)
				.amount(amount)
				.balance(last.getBalance() + amount)
				.counterpartyName(counterpartyName)
				.code(code)
				.cnt(cnt)
				.build()
		);
	}

	public List<ParkingDetail> getParkingDetails(Parking parking) {
		return parkingDetailsRepository.findAllByParkingOrderByCreatedAtDesc(parking).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_DETAIL_NOT_EXIST));
	}
}
