package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StocksRepository extends JpaRepository<Stock,Integer> {

}
