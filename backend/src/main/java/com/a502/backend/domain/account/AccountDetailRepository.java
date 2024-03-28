package com.a502.backend.domain.account;

import com.a502.backend.application.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
    @Query("SELECT ad FROM AccountDetail ad WHERE ad.createdAt >= :startDate AND ad.createdAt <= :endDate AND ad.account.accountUuid = :accountUuid")
    List<AccountDetail> findAllByDateRangeAndAccountUuid(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("accountUuid")
    UUID accountUuid);

}
