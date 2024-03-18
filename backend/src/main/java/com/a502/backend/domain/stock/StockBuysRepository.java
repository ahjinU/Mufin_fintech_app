package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.StockBuy;
import org.springframework.data.jpa.repository.JpaRepository;

interface StockBuysRepository extends JpaRepository<StockBuy, Integer> {
}
