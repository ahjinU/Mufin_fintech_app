package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
	// 주식 하나당 매수 주문 들어온 것 리스트 조회
	List<StockSell> findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(int id, int cnt, LocalDateTime localDateTime);

	Optional<List<StockSell>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);

	// 미체결 매도 주문 조회
	Optional<List<StockSell>> findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(User user, Code code, LocalDateTime localDateTime, int cnt);
	List<StockSell> findAllByUserAndStockAndCode(User user, Stock stock, Code code);
}
