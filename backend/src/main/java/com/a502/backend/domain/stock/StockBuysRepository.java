package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface StockBuysRepository extends JpaRepository<StockBuy, Integer> {
	@Query(value = "SELECT * FROM stock_buys WHERE created_at = CURRENT_DATE AND stock_buy_id = :id AND cnt_not > 0", nativeQuery = true)
	List<StockBuy> getBuyList(int id);
}
