package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockHolding;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface StockHoldingsRepository extends JpaRepository<StockHolding, StockHoldingsId> {

}
