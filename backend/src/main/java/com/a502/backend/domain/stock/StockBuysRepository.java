package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface StockBuysRepository extends JpaRepository<StockBuy, Integer> {
    Optional<List<StockBuy>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);
}
