package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.*;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockSellsService {

	private final StockSellsRepository stockSellsRepository;

	public StockSell findById(int id) {
		return stockSellsRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCKSELL_NOT_EXIST));
	}

	public List<StockSell> getSellOrderList(int id, int cnt, LocalDateTime localDateTime) {
		return stockSellsRepository.findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(id, cnt, localDateTime);
	}

	@Transactional
	public StockSell save(User user, Stock stock, int price, int cntTotal, Code code) {
		return stockSellsRepository.save(StockSell.builder()
				.user(user)
				.stock(stock)
				.price(price)
				.cntTotal(cntTotal)
				.code(code)
				.build());
	}

//	@Transactional
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<StockSell> findTransactionList(Stock stock, int price) {
		return stockSellsRepository.findAllByStockAndPriceOrderByCreatedAtAsc(stock, price).orElse(null);
	}

	@Transactional
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public void stockSell(StockSell stocksell, int cnt, Code code) {
//		StockSell ss = stockSellsRepository.findById(stocksell.getId()).orElse(null);
		int cntNot = stocksell.getCntNot();
		if (cntNot - cnt < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCKSELL_STOCK_IS_NOT_ENOUGH);
		stocksell.setCntNot(cntNot - cnt);
        if (cntNot - cnt == 0)
			stocksell.updateCode(code);
		stockSellsRepository.saveAndFlush(stocksell);
	}

	public List<StockSell> getWaitingStockOrders(User user, Code code, LocalDateTime localDateTime, int cnt) {
		return stockSellsRepository.findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(user, code, localDateTime, 0).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_STOCKSELL_NOT_EXIST));
	}

	@Transactional
	public int getStockSellWaitingList(User user, Stock stock, Code code){
		List<StockSell> list = stockSellsRepository.findAllByUserAndStockAndCode(user, stock, code);
		int cntNot = 0;
		for (StockSell sell : list){
			cntNot += sell.getCntNot();
		}
		return cntNot;
	}

	public List<StockSell> getStockTransListByStock(Stock stock){
		return stockSellsRepository.findAllByStock(stock);
	}

}
