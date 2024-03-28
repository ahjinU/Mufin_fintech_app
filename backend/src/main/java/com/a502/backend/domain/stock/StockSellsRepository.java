package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
	// 주식 하나당 매수 주문 들어온 것 리스트 조회
	List<StockSell> findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(int id, int cnt, LocalDateTime localDateTime);
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	Optional<List<StockSell>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);

	// 미체결 매도 주문 조회
	Optional<List<StockSell>> findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(User user, Code code, LocalDateTime localDateTime, int cnt);
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	List<StockSell> findAllByUserAndStockAndCode(User user, Stock stock, Code code);
	List<StockSell> findAllByStock(Stock stock);

	void deleteAll();
}
