package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockBuysService {
    private final StockBuysRepository stockBuysRepository;

    public StockBuy findById(int id){
        return stockBuysRepository.findById(id).orElseThrow(
                () -> BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST));
    }

    @Transactional
    public StockBuy save(int price, int cntTotal, Stock stock, User user){
        return stockBuysRepository.save(new StockBuy(price, cntTotal, stock, user));
    }

    public List<StockBuy> getBuyList(int id){
        return stockBuysRepository.getBuyList(id);
    }
}
