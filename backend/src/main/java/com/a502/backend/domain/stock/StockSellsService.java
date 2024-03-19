package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
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
public class StockSellsService {

    private final StockSellsRepository stockSellsRepository;

    public StockSell findById(int id){
        return stockSellsRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCKSELL_NOT_EXIST));
    }

    @Transactional
    public StockSell save(User user, Stock stock, int price, int cntTotal){
        return stockSellsRepository.save(new StockSell(price, cntTotal, stock, user));
    }

    public List<StockSell> findTransactionList(Stock stock, int price){
        return stockSellsRepository.findAllByStockAndPriceOrderByCreatedAtAsc(stock, price).orElse(null);
    }

    public void stockSell(StockSell stocksell, int cnt){
    }


}
