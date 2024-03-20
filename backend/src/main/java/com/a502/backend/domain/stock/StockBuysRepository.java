package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.List;
import java.util.Optional;

interface StockBuysRepository extends JpaRepository<StockBuy, Integer> {
	// 주식 하나당 매도 주문 들어온 것 리스트 조회
	List<StockBuy> findAllByStock_IdAndCntNotGreaterThanAndCreatedAtGreaterThan(int id, int cnt , LocalDateTime localDateTime);
    Optional<List<StockBuy>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);
}
