package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

interface StockDetailsRepository extends JpaRepository<StockDetail, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	StockDetail findTopByStockOrderByCreatedAtDesc(Stock stock);

	List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock, Pageable pageable);

	List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock);
}
