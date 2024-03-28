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

	List<CashDetail> findAllByUserAndTransAtBetween(User user, LocalDateTime start, LocalDateTime end);;
}
