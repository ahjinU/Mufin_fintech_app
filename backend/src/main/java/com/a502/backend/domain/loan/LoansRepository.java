package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LoansRepository extends JpaRepository<Loan, Integer> {

//	@Query("select l from Loan l where l.child = :child and l.deleted = false")
//	Optional<List<Loan>> getLoanChild(User child);
}
