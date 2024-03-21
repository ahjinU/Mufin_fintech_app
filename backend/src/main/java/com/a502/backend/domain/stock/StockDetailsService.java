package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockDetailsService {
	private final StockDetailsRepository stockDetailsRepository;

	@Transactional
	public StockDetail getLastDetail(Stock stock) {
		return stockDetailsRepository.findTopByStockOrderByCreatedAtDesc(stock);
	}

	@Transactional
	public void validStockPrice(Stock stock, int price) {
		StockDetail stockDetail = getLastDetail(stock);
		if (stockDetail.getLowerLimitPrice() > price
				|| stockDetail.getUpperLimitPrice() < price)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCK_NOT_EXIST);
	}

	@Transactional
	public void updateStockDetail(Stock stock, int price) {
		StockDetail stockDetail = getLastDetail(stock);
		stockDetail.setPrice(price);
		stockDetail.setHighestPrice(Math.max(stockDetail.getHighestPrice(), price));
		stockDetail.setLowestPrice(Math.min(stockDetail.getLowestPrice(), price));
	}

	public List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock, Pageable pageable) {
		return stockDetailsRepository.findAllByStockOrderByCreatedAtDesc(stock, pageable);
	}

	public List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock){
		return stockDetailsRepository.findAllByStockOrderByCreatedAtDesc(stock);
	}
}
