package com.a502.backend.domain.savings;

import com.a502.backend.application.entity.Savings;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {
	@Override
	<S extends Savings> S save(S entity);

	@Query("select s from Savings s where s.savingUuid = :savingsUuid")
	Optional<Savings> findSavingsListByUuid(UUID savingsUuid);

	@Query("select s from Savings s where s.isDeleted = false")
	List<Savings> findAllByParents(User parents);
}
