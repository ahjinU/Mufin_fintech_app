package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {

	@Query("select ad from AccountDetail ad where ad.account = :account and ad.amount>0")
	List<AccountDetail> findSavingsAccountDetail(Account account);
}
