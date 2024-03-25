package com.a502.backend.domain.payment;

import com.a502.backend.application.entity.AccountDetail;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class AccountDetailService {
	AccountDetailRepository accountDetailRepository;
	
	// 거래 내역 등록
	public AccountDetail save(AccountDetail accountDetail) {
		return accountDetailRepository.save(accountDetail);
	}
}
