package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface LoansRepository extends JpaRepository<Loan, Integer> {

	@Query("select l from Loan l where l.child = :user and l.code.id != 'L004' order by l.createdAt desc")
	List<Loan> findAllLoansInProgressByUser(User user);

	@Query("select l from Loan l where l.loanUuid = :loanUuid")
	Optional<Loan> findByUuid(UUID loanUuid);

	@Query("select l from Loan l where l.parent = :parent and (l.code.id = 'L002' or l.code.id ='L004') order by l.createdAt desc")
	List<Loan> findAllLoansInProgressByParents(User parent);

	@Query("select l from Loan l where l.parent = :parent and l.code.id = 'L001' order by l.createdAt desc")
	List<Loan> findRequestedLoansByParents(User parent);
}
