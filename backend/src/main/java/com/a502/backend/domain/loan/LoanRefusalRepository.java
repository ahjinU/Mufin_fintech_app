package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.LoanRefusal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface LoanRefusalRepository extends JpaRepository<LoanRefusal, Integer> {
	@Query("select lf from LoanRefusal lf where lf.loan = :loan")
	LoanRefusal findByLoan(Loan loan);
}
