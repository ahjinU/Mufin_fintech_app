package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StocksService {
    private final StocksRepository stocksRepository;

    public Stock findById(int id){
        return stocksRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCK_NOT_EXIST));
    }

}
