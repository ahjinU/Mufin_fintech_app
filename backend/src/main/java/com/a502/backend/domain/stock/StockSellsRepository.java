package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.StockSell;
import org.springframework.data.jpa.repository.JpaRepository;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
}
