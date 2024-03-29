package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockHolding;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockHoldingsService {
    private final StockHoldingsRepository stockHoldingsRepository;
    private final StocksService stocksService;

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
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
        int unitPrice = stockHoldingTotal / stockHoldingCnt;

        stockHolding.setCnt(stockHoldingCnt - cnt);
        stockHolding.setTotal(unitPrice * (stockHoldingCnt - cnt));
        stockHoldingsRepository.saveAndFlush(stockHolding);
    }

    @Transactional
    public void stockBuy(User user, Stock stock, int cnt, int price){
        StockHolding stockHolding = findById(user, stock);
        int stockHoldingCnt = stockHolding.getCnt();
        int stockHoldingTotal = stockHolding.getTotal();
        int unitPrice = stockHoldingTotal / stockHoldingCnt;

        stockHolding.setCnt(stockHoldingCnt + cnt);
        stockHolding.setTotal(unitPrice * (stockHoldingCnt + cnt));
        stockHoldingsRepository.saveAndFlush(stockHolding);
    }

    // 유저가 가진 주식 조회
    public List<StockHolding> findAllByUser(User user){
        return stockHoldingsRepository.findAllByUser(user).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST));
    }

    @Transactional
    public void initStockHolding(User user, List<Stock> stocks,HashMap<String, Integer> stockStartPriceList) {

        stocks.forEach(stock -> {
            StockHolding holding = StockHolding.builder()
                    .stock(stock)
                    .user(user)
                    .cnt(10)
                    .total(stockStartPriceList.get(stock.getName())*10)
                    .build();

            stockHoldingsRepository.save(holding);

        });
    }

    public void save(StockHolding stockHolding) {
        stockHoldingsRepository.save(stockHolding);
    }

}
