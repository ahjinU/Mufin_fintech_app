package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Ranking;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RankingRepository extends CrudRepository<Ranking, Integer> {
    Optional<Ranking> findTopOrderByIdDesc();
}
