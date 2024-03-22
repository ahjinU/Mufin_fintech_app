package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface StockDetailsRepository extends JpaRepository<StockDetail, Integer> {
	StockDetail findTopByStockOrderByCreatedAtDesc(Stock stock);

	List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock, Pageable pageable);

	List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock);
}
