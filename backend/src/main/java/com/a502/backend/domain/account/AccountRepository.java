package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
interface AccountRepository extends JpaRepository<Account, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("select a from Account a where a.accountNumber = :accountNumber order by a.createdAt desc")
	Optional<List<Account>> findByAccountNumber(String accountNumber);
	boolean existsByAccountNumber(String accountNumber);
	boolean existsByUserAndTypeCode(User user, Code code);
}
