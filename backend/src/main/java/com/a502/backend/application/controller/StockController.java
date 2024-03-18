package com.a502.backend.application.controller;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.domain.stock.StocksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/api/stock")
public class StockController {
	private final SimpMessageSendingOperations sendingOperations;
	private Stock stock;
	@MessageMapping("/enter")
	public ResponseEntity<?> enter(@RequestBody Stock stock){
		if(stock.getName().equals("주식이름")){
		sendingOperations.convertAndSend("/sub/stock/"+stock.getName(),stock);
		}
		return new ResponseEntity<String>(stock.getName(), HttpStatus.OK);
	}
}
