package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockBuysService {
	private final StockBuysRepository stockBuysRepository;

	public StockBuy findById(int id) {
		return stockBuysRepository.findById(id).orElseThrow(
				() -> BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST));
	}

	@Transactional
	public StockBuy save(User user, Stock stock, int price, int cntTotal, Code code) {
		return stockBuysRepository.save(StockBuy.builder()
				.user(user)
				.price(price)
				.cntTotal(cntTotal)
				.stock(stock)
				.price(price)
				.code(code)
				.build());
	}

	public List<StockBuy> getBuyOrderList(int id, int cnt, LocalDateTime localDateTime) {
		return stockBuysRepository.findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThanOrderByPriceDesc(id, cnt, localDateTime);
	}

	//	@Transactional
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<StockBuy> findTransactionList(Stock stock, int price) {
		return stockBuysRepository.findAllByStockAndPriceOrderByCreatedAtAsc(stock, price);
	}

	@Transactional
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public void stockBuy(StockBuy stockBuy, int cnt, Code code) {
		int cntNot = stockBuy.getCntNot();
		if (cntNot - cnt < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_STOCK_IS_NOT_ENOUGH);
		stockBuy.setCntNot(cntNot - cnt);
		if (cntNot - cnt == 0)
			stockBuy.updateCode(code);
		stockBuysRepository.saveAndFlush(stockBuy);
	}

	public List<StockBuy> getTodayTransactions(Stock stock, LocalDateTime localDateTime) {
		List<StockBuy> stockBuyList = stockBuysRepository.findAllByStockAndCreatedAtGreaterThan(stock, localDateTime);
		return stockBuyList;
	}

	public List<StockBuy> getWaitingStockOrders(User user, Code code, LocalDateTime localDateTime, int cnt) {
		List<StockBuy> stockBuyList = stockBuysRepository.findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(user, code, localDateTime, cnt);
		if (stockBuyList.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST);
		return stockBuyList;
	}

	@Transactional
	public int getStockBuyWaitingList(User user, Stock stock, Code code) {
		List<StockBuy> list = stockBuysRepository.findAllByUserAndStockAndCode(user, stock, code);
		int price = 0;
		for (StockBuy stockBuy : list) {
			price += stockBuy.getPrice() * stockBuy.getCntNot();
		}
		return price;
	}

	public List<StockBuy> getStockTransListByStock(Stock stock) {
		return stockBuysRepository.findAllByStock(stock);
	}

	public List<StockBuy> getStockTransListOpend() {
		return stockBuysRepository.findAllTransactionIsOpened();
	}

	public void updateCode(StockBuy stockBuy, Code code) {
		stockBuy.updateCode(code);
		stockBuysRepository.saveAndFlush(stockBuy);
	}
}
