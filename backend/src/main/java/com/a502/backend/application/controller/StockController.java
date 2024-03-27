package com.a502.backend.application.controller;

import com.a502.backend.application.entity.RankingDetail;
import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.domain.stock.request.StockPriceHistoryRequest;
import com.a502.backend.domain.stock.response.*;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_PRICE_HISTORY_LINE, result));
	}

	// 주식별 주가 기간 조회(봉그래프)
	@PostMapping("/price/history/bar")
	public ResponseEntity<ApiResponse<List<StockPriceHistoryByBar>>> getStockGraphInfosByBar(@RequestBody StockPriceHistoryRequest request) {
		List<StockPriceHistoryByBar> result = stockFacade.getStockGraphInfosByBar(request.getName(), request.getPeriod());
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_PRICE_HISTORY_BAR, result));
	}

	// 전체 주식 정보 조회
	@PostMapping("/all")
	public ResponseEntity<ApiResponse<TotalStockListResponse>> getAllStock() {
		TotalStockListResponse result = stockFacade.getTotalStockList();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_GET_ALL_INFO, result));
	}

	// 보유 주식 정보 조회
	@PostMapping("/mine")
	public ResponseEntity<ApiResponse<MyStockListResponse>> getMyStocks() {
		MyStockListResponse result = stockFacade.getMyStockList();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_MINE, result));
	}

	// 미체결 주식 정보 조회
	@PostMapping("/order/wait")
	public ResponseEntity<ApiResponse<MyWaitingStockOrderResponse>> getMyWaitingStockOrders() {
		MyWaitingStockOrderResponse result = stockFacade.getMyWaitingStockOrder();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_GET_WAITING_INFO, result));
	}

	@GetMapping("/ranking/user")
	public ResponseEntity<ApiResponse<RankingDetail>> getRanking(@RequestParam(value = "userId") int userId) {
		RankingDetail result = stockFacade.getRanking(userId);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_RANKING_USER, result));
	}

	@GetMapping("/ranking/total")
	public ResponseEntity<ApiResponse<RankingResponse>> getRankingList() {
		RankingResponse result = stockFacade.getRanknigList();
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_RANKING_LIST, result));
	}

	// 주식 상세 정보 조회(1개)
	@PostMapping("/detail")
	public ResponseEntity<ApiResponse<StockInfoResponse>> getStockInfo(@RequestParam String name) {
		StockInfoResponse result = stockFacade.getStockInfo(name);
		return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_STOCK_GET_ONE_INFO, result));
	}
}

