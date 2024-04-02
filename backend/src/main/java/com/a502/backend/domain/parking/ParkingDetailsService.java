package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.*;
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
	@Transactional
	public ParkingDetail saveInterest(Parking parking, int amount, Code code) {
		ParkingDetail last = getLastDetail(parking);
		return parkingDetailsRepository.save(ParkingDetail.builder()
				.parking(parking)
				.amount(amount)
				.balance(last.getBalance() + amount)
				.counterpartyName("쟈수민의 요술 오븐")
				.code(code)
				.cnt(0)
				.ratio(parking.getInterest())
				.build()
		);
	}

	public List<ParkingDetail> getParkingDetails(Parking parking) {
		List<ParkingDetail> parkingDetailsList = parkingDetailsRepository.findAllByParkingOrderByCreatedAtDesc(parking);
		if (parkingDetailsList.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_PARKING_DETAIL_NOT_EXIST);
		return parkingDetailsList;
	}

	@Transactional
	public void initSeadMoney(Parking newParkingAccount, Code code) {

		String counterpartyName = "머핀";

		ParkingDetail initParkingDetail = ParkingDetail.builder()
				.parking(newParkingAccount)
				.balance(newParkingAccount.getBalance())
				.counterpartyName(counterpartyName)
				.code(code)
				.build();

		parkingDetailsRepository.save(initParkingDetail);
	}
}
