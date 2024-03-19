package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

interface StockSellsRepository extends JpaRepository<StockSell, Integer> {
	@Query(value = "SELECT * FROM stock_sells WHERE created_at = CURRENT_DATE AND stock_sells_id = :id AND cnt_not > 0", nativeQuery = true)
	List<StockSell> getSellList(int id);
    Optional<List<StockSell>> findAllByStockAndPriceOrderByCreatedAtAsc(Stock stock, int price);
}
