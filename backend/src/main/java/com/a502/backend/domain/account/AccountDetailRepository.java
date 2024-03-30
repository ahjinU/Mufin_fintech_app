package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {

	@Query("select ad from AccountDetail ad where ad.account = :account and ad.amount>0")
	List<AccountDetail> findSavingsAccountDetail(Account account);


    List<AccountDetail> findAllByAccountUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);

    Optional<AccountDetail> findAccountDetailByAccountDetailUuid(UUID transactionUUID);
}
