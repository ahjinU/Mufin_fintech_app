package com.a502.backend.domain.payment;

import com.a502.backend.application.entity.Account;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;

	public Account findByAccountNumber(String accountNumber) {
		List<Account> accounts = accountRepository.findByAccountNumber(accountNumber).
				orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_NOT_EXIST));
		if (accounts.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_NOT_EXIST);
		return accounts.get(0);
	}
}
