package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoanDetailRepository extends JpaRepository<LoanDetail, Integer> {

	@Override
	<S extends LoanDetail> S save(S entity);
}
