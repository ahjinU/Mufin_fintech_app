package com.a502.backend.application.controller;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.domain.stock.request.StockPriceHistoryRequest;
import com.a502.backend.domain.stock.response.PriceAndStockOrderList;
import com.a502.backend.domain.stock.response.StockPriceHistoryByBar;
import com.a502.backend.domain.stock.response.StockPriceHistoryByLine;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {
	private final SimpMessageSendingOperations sendingOperations;
	private final StockFacade stockFacade;

	// 해당 주식 정보 조회
	@MessageMapping("/orders/{name}")
	public void getStockOrderInfo(@DestinationVariable String name) {
		PriceAndStockOrderList result = stockFacade.getStockOrderInfo(name);
		sendingOperations.convertAndSend("/sub/orders/" + name, result);
	}

	@PostMapping("/buy")
	public ResponseEntity<ApiResponse<Void>> stockBuy(int userId, String name, int price, int cnt_total) {
		// 주식 매수 주문 넣을 때마다 sub/orders/name 으로 데이터 보내주기
		PriceAndStockOrderList result = stockFacade.getStockOrderInfo(name);
		sendingOperations.convertAndSend("/sub/orders/" + name, result);
		stockFacade.stockBuy(userId, name, price, cnt_total);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_BUY));
	}

	@PostMapping("/sell")
	public ResponseEntity<ApiResponse<Void>> stockSell(int userId, String name, int price, int cnt_total) {
		// 주식 매도 주문 넣을 때마다 sub/orders/name 으로 데이터 보내주기
		PriceAndStockOrderList result = stockFacade.getStockOrderInfo(name);
		sendingOperations.convertAndSend("/sub/orders/" + name, result);
		stockFacade.stockSell(userId, name, price, cnt_total);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_SELL));
	}

	// 주식별 주가 기간 조회(선그래프)
	@PostMapping("/price/history/line")
	public ResponseEntity<ApiResponse<List<StockPriceHistoryByLine>>> getStockGraphInfosByLine(@RequestBody StockPriceHistoryRequest request) {
		List<StockPriceHistoryByLine> result = stockFacade.getStockGraphInfosByLine(request.getName(), request.getPeriod());
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_PRICE_HISTORY_LINE));
	}

	// 주식별 주가 기간 조회(봉그래프)
	@PostMapping("/price/history/bar")
	public ResponseEntity<ApiResponse<List<StockPriceHistoryByBar>>> getStockGraphInfosByBar(@RequestBody StockPriceHistoryRequest request) {
		List<StockPriceHistoryByBar> result = stockFacade.getStockGraphInfosByBar(request.getName(), request.getPeriod());
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_PRICE_HISTORY_BAR));
	}

	@GetMapping("ranking/mine")
	public void getRanking(int userId){

//		sendingOperations.convertAndSend("/sub/ranks/" + , result);
	}
}

