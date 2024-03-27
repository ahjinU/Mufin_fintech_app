package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowanceRepository extends JpaRepository<AccountDetail, Integer> {

}
