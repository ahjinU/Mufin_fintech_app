package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockHolding;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.List;
import java.util.Optional;

interface StockHoldingsRepository extends JpaRepository<StockHolding, StockHoldingsId> {
    List<StockHolding> findAllByUser(User user);
}
