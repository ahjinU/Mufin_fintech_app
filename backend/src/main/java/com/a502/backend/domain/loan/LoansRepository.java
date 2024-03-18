package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoansRepository extends JpaRepository<Loan, Integer> {
}
