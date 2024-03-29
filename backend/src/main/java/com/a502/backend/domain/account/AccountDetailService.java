package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
	private final AccountDetailRepository accountDetailRepository;

	// 거래 내역 등록
	public AccountDetail save(AccountDetail accountDetail) {
		return accountDetailRepository.save(accountDetail);
	}

	// 거래 내역 조회하기
	public List<AccountDetail> findSavingsAccountDetail(Account account) {
		List<AccountDetail> result = accountDetailRepository.findSavingsAccountDetail(account);
		if (result.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_DETAIL_NOT_EXIST);
		return result;
	}
}
