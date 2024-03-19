package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

interface StockDetailsRepository extends JpaRepository<StockDetail, Integer> {
    StockDetail findTopByStockOrderByCreatedAtDesc(Stock stock);
}
