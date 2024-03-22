package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.ParkingDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.parking.response.MyParkingInfoResponse;
import com.a502.backend.domain.parking.response.ParkingDetailList;
import com.a502.backend.domain.parking.response.ParkingDetailListResponse;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@RequestMapping("/api/parking")
public class ParkingFacade {
	private final ParkingDetailsService parkingDetailsService;
	private final ParkingService parkingService;
	private final UserService userService;
	private final CodeService codeService;

	// 파킹통장 내역 조회
	@PostMapping("/history")
	public ParkingDetailListResponse getParkingDetails() {
		User user = userService.userFindByEmail();
		Parking parking = parkingService.findByUser(user);
		// 파킹 통장 거래내역
		List<ParkingDetail> parkingDetail = parkingDetailsService.getParkingDetails(parking);

		List<ParkingDetailList> result = new ArrayList<>();

		for (ParkingDetail pd : parkingDetail) {
			// 주식명
			String transName = pd.getCounterpartyName();
			// 타입(이자,매도,매수)
			String type = codeService.findById(pd.getCode().getId()).getName();
			// 체결 수
			int cnt = pd.getCnt();
			// 가격
			int price = pd.getAmount() / cnt;
			// 파킹통장 이자율
			double ratio = pd.getRatio();
			// 날짜
			LocalDate date = pd.getCreatedAt().toLocalDate();

			result.add(ParkingDetailList.builder()
					.transName(transName)
					.type(type)
					.cnt(cnt)
					.price(price)
					.ratio(ratio)
					.date(date)
					.build());
		}
		return ParkingDetailListResponse.builder()
				.transaction(result)
				.build();
	}

	// 파킹 통장 정보 조회
	public MyParkingInfoResponse getMyParkingInfo() {
		User user = userService.userFindByEmail();
		Parking parking = parkingService.findByUser(user);
		// 파킹 통장 잔액
		int balanceToday = parking.getBalance();
		// 이자율
		double ratio = parking.getInterest();
		// 내일 받을 수 있는 이자
		int interest = (int) (balanceToday * ratio) / 365;
		// 내일 통장 예정 잔액
		int balanceTmrw = balanceToday + interest;
		return MyParkingInfoResponse.builder()
				.balanceToday(balanceToday)
				.balanceTmrw(balanceTmrw)
				.interest(interest)
				.ratio(ratio)
				.build();
	}

}
