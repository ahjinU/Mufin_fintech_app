package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockHolding;
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
public class StockHoldingsService {
    private final StockHoldingsRepository stockHoldingsRepository;
    private final StocksService stocksService;

    public StockHolding findById(User user, Stock stock){
        return stockHoldingsRepository.findById(StockHoldingsId.builder()
                        .user(user)
                        .stock(stock)
                        .build())
                .orElseThrow(
                        () -> BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST));
    }

    @Transactional
    public int getStockHolding(User user, Stock stock){
        StockHolding stockHolding = findById(user, stock);
        return stockHolding.getCnt();
    }

    @Transactional
    public void stockSell(User user, Stock stock, int cnt, int price){
        StockHolding stockHolding = findById(user, stock);
        int stockHoldingCnt = stockHolding.getCnt();
        int stockHoldingTotal = stockHolding.getTotal();

        stockHolding.setCnt(stockHoldingCnt - cnt);
        stockHolding.setTotal(stockHoldingTotal - cnt * price);
    }

    @Transactional
    public void stockBuy(User user, Stock stock, int cnt, int price){
        StockHolding stockHolding = findById(user, stock);
        int stockHoldingCnt = stockHolding.getCnt();
        int stockHoldingTotal = stockHolding.getTotal();

        stockHolding.setCnt(stockHoldingCnt + cnt);
        stockHolding.setTotal(stockHoldingTotal + cnt * price);
    }

    // 유저가 가진 주식 조회
    public List<StockHolding> findAllByUser(User user){
        return stockHoldingsRepository.findAllByUser(user).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST));
    }


    public void save(StockHolding stockHolding) {
        stockHoldingsRepository.save(stockHolding);
    }

}
