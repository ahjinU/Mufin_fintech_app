package com.a502.backend.domain.account;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
    List<AccountDetail> findAllByAccountUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);

}
