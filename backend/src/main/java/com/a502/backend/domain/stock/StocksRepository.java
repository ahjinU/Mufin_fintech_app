package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface StocksRepository extends JpaRepository<Stock,Integer> {
    Optional<Stock> findByName(String name);
    List<Stock> findAll();

}
