package com.a502.backend.domain.user;

import com.a502.backend.application.entity.StockBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<StockBuy.User, Integer> {
}
