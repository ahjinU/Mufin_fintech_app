package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.StockSell;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockSellsService {

    private final StockSellsRepository stockSellsRepository;

    public StockSell findById(int id){
        return stockSellsRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCKSELL_NOT_EXIST));
    }
}
