package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
	// 주식 하나당 매수 주문 들어온 것 리스트 조회
	List<StockSell> findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThanOrderByPriceDesc(int id, int cnt, LocalDateTime localDateTime);
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	@Query("select ss from StockSell ss where ss.code.id = 'S001' and ss.stock = :stock and ss.price = :price order by ss.createdAt asc")
	List<StockSell> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);

	// 미체결 매도 주문 조회
	List<StockSell> findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThanOrderByPriceDesc(User user, Code code, LocalDateTime localDateTime, int cnt);
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	List<StockSell> findAllByUserAndStockAndCode(User user, Stock stock, Code code);
	List<StockSell> findAllByStock(Stock stock);
	@Query("select ss from StockSell ss where ss.code.id = 'S001'")
	List<StockSell> findAllTransactionIsOpened();
	void deleteAll();
}
