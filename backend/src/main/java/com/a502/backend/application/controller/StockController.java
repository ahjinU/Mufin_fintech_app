package com.a502.backend.application.controller;

import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {
    private final StockFacade stockFacade;

    @PostMapping("/buy")
    public ResponseEntity<ApiResponse<Void>> stockBuy(int userId, String name, int price, int cnt_total){
        stockFacade.stockBuy(userId, name, price, cnt_total);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_DOMAIN_METHOD));
    }
}
