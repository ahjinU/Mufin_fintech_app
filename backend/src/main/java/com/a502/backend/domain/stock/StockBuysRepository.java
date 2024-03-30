package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.List;
import java.util.Optional;

interface StockBuysRepository extends JpaRepository<StockBuy, Integer> {
	// 주식 하나당 매도 주문 들어온 것 리스트 조회
	List<StockBuy> findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(int id, int cnt , LocalDateTime localDateTime);
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	@Query("select sb from StockBuy sb where sb.code.id = 'S001' and sb.stock = :stock and sb.price = :price order by sb.createdAt asc")
	List<StockBuy> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);

	// 주식별 설정 기간 이후 총 매수 주문 조회
	List<StockBuy> findAllByStockAndCreatedAtGreaterThan(Stock stock, LocalDateTime localDateTime);

	// 미체결 주식 주문 조회
	List<StockBuy> findAllByUserAndCodeAndCreatedAtGreaterThanAndCntNotGreaterThan(User user, Code code, LocalDateTime localDateTime, int cnt);

//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
	List<StockBuy> findAllByUserAndStockAndCode(User user, Stock stock, Code code);
	List<StockBuy> findAllByStock(Stock stock);
	@Query("select sb from StockBuy sb where sb.code.id = 'S001'")
	List<StockBuy> findAllTransactionIsOpened();
	void deleteAll();
}
