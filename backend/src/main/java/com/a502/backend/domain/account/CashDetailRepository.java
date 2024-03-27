package com.a502.backend.domain.account;

import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface CashDetailRepository extends JpaRepository<CashDetail, Integer> {
	@Query("select cd from CashDetail cd where cd.user = :user and cd.transAt between :startDay and :endDay")
	Optional<List<CashDetail>> findByUserAndPeriod(User user, LocalDateTime startDay, LocalDateTime endDay);
}
