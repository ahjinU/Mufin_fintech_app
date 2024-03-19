package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockSell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
    Optional<List<StockSell>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);
}
