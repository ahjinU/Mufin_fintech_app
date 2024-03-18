package com.a502.backend.application.controller;

import com.a502.backend.application.entity.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Arrays;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {
	private final SimpMessageSendingOperations sendingOperations;
	private Stock stock;

	@MessageMapping("/enter")
	public ResponseEntity<?> enter(@RequestBody Stock stock) {
		if (stock.getName().equals("주식이름")) {
			sendingOperations.convertAndSend("/sub/stock/" + stock.getName(), stock);
		}
		return new ResponseEntity<String>(stock.getName(), HttpStatus.OK);
	}

	private final StockFacade stockFacade;

	@PostMapping("/buy")
	public ResponseEntity<ApiResponse<Void>> stockBuy(int userId, String name, int price, int cnt_total) {
		stockFacade.stockBuy(userId, name, price, cnt_total);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_DOMAIN_METHOD));
	}
}

