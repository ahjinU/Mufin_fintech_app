package com.a502.backend.application.controller;

import com.a502.backend.application.facade.ParkingFacade;
import com.a502.backend.domain.parking.response.MyParkingInfoResponse;
import com.a502.backend.domain.parking.response.ParkingDetailListResponse;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/parking")
public class ParkingController {
	private final ParkingFacade parkingFacade;

	// 파킹 통장 거래 내역 조회
	@PostMapping("/history")
	public ResponseEntity<ApiResponse<ParkingDetailListResponse>> getParkingDetails() {
		ParkingDetailListResponse result = parkingFacade.getParkingDetails();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PARKING_DETAILS_GET, result));
	}

	// 내 파킹통장 정보 조회
	@PostMapping("/account")
	public ResponseEntity<ApiResponse<MyParkingInfoResponse>> getMyParkingInfos(){
		MyParkingInfoResponse result = parkingFacade.getMyParkingInfo();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PARKING_GET_INFO,result));
	}
}
