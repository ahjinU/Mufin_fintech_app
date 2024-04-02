package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.Savings;
import com.a502.backend.application.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface AccountRepository extends JpaRepository<Account, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("select a from Account a where a.accountNumber = :accountNumber order by a.createdAt desc")
	Optional<List<Account>> findByAccountNumber(String accountNumber);
	boolean existsByAccountNumber(String accountNumber);
	boolean existsByUserAndTypeCode(User user, Code code);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("select a from Account a where a.user = :user and a.typeCode.id = 'AT001' and a.statusCode.id = 'AS002' order by a.createdAt desc")
	Optional <Account> findByUser(User user);

	@Query("select a from Account a where a.savings = :savings and (a.statusCode.id = 'AS001' or a.statusCode.id = 'AS002')")
	List<Account> findAllBySavings(Savings savings);

	@Query("select a from Account a where a.user = :child and a.typeCode.id = 'AT002' and (a.statusCode.id = 'AS002' or a.statusCode.id = 'AS004') and a.isDeleted = false")
	List<Account> findAllSavingsByChild(User child);

	@Query("select a from Account a where a.accountUuid = :uuid and a.typeCode.id = 'AT002' and a.statusCode.id = 'AS002'")
	Optional<Account> findByAccountUuid(UUID uuid);

	@Query("select a from Account a where a.typeCode.id = 'AT002' and a.statusCode.id = 'AS002' and a.isDeleted = false")
	List<Account> findAllSavingAccount();

	@Query("select a from Account a where a.accountUuid = :uuid and a.savings != null and a.typeCode.id = 'AT002' and a.statusCode.id = 'AS001'")
	Optional<Account>findExpiredSavingsAccountByUuid(UUID uuid);

	List<Account> findByUserAndStatusCodeAndTypeCode(User user, Code statusCode, Code typeCode);

	@Query("select a from Account a where a.user = :user and a.typeCode.id = 'AT001' and a.statusCode.id = 'AS002'")
	Optional<Account> findDefaultAccountByUser(User user);
}

