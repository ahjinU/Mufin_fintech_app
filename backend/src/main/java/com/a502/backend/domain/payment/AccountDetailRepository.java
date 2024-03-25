package com.a502.backend.domain.payment;

import com.a502.backend.application.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
	@Override
	<S extends AccountDetail> S save(S entity);
}
