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
@Transactional(readOnly = true)
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
				.price(price)
				.cntTotal(cntTotal)
				.stock(stock)
				.price(price)
				.code(code)
				.build());
	}

	//    public List<StockBuy> getBuyList(int id){
//        return stockBuysRepository.getBuyList(id);
//    }
	public List<StockBuy> getBuyOrderList(int id, int cnt, LocalDateTime localDateTime) {
		return stockBuysRepository.findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(id, cnt, localDateTime);
	}

	public List<StockBuy> findTransactionList(Stock stock, int price) {
		return stockBuysRepository.findAllByStockAndPriceOrderByCreatedAtAsc(stock, price).orElse(null);
	}

	@Transactional
	public void stockBuy(StockBuy stockBuy, int cnt, Code code) {
		int cntNot = stockBuy.getCntNot();
		if (cntNot - cnt < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_STOCK_IS_NOT_ENOUGH);
		stockBuy.setCntNot(cntNot - cnt);
        if (cntNot - cnt == 0)
            stockBuy.updateCode(code);
	}

	public List<StockBuy> getTodayTransactions(Stock stock, LocalDateTime localDateTime) {
		return stockBuysRepository.findAllByStockAndCreatedAtGreaterThan(stock, localDateTime).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST));
	}

	public List<StockBuy> getWaitingStockOrders(User user, Code code, LocalDateTime localDateTime, int cnt){
		return stockBuysRepository.findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(user, code, localDateTime, cnt).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST));
	}

	public int getStockBuyWaitingList(User user, Stock stock, Code code){
		List<StockBuy> list = stockBuysRepository.findAllByUserAndStockAndCode(user, stock, code);
		int price = 0;
		for(StockBuy stockBuy : list){
			price += stockBuy.getPrice() * stockBuy.getCntNot();
		}
		return price;
	}


}
