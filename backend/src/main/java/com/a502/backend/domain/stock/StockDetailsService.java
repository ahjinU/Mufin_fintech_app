package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockDetailsService {
    private final StockDetailsRepository stockDetailsRepository;

    @Transactional
    public void validStockPrice(Stock stock, int price){
        StockDetail stockDetail = stockDetailsRepository.findTopByStockOrderByCreatedAtDesc(stock);
        if (stockDetail.getLowerLimitPrice() > price
            || stockDetail.getUpperLimitPrice() < price)
            throw BusinessException.of(ErrorCode.API_ERROR_STOCK_NOT_EXIST);
    }

    public void stockTransaction(Stock stock, int price){

    }
}
