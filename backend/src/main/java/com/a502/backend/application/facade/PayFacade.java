package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.payment.AccountService;
import com.a502.backend.domain.payment.request.RecipientAccount;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PayFacade {
	private final AccountService accountService;

	// 송금받는 사람 유효성 체크
	@Transactional
	public String checkRecipient(RecipientAccount recipientAccount) throws BusinessException {
		String accountNumber = recipientAccount.getAccountNumberIn();
		Account account = accountService.findByAccountNumber(accountNumber);
		if (account.isDeleted() || account.getStatusCode().getName().equals("정지"))
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_NOT_EXIST);
		User recipient = account.getUser();
		return recipient.getName();
	}

	// 송금하는 사람 유효성 체크
	@Transactional
	public void checkMyAccount(String accountNumberOut, int amount) {
		Account account = accountService.findByAccountNumber(accountNumberOut);
		// 계좌가 존재하지 않는 경우
		if (account.isDeleted())
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_NOT_EXIST);
		// 계좌가 정지상태인 경우
		if (account.getStatusCode().getName().equals("정지"))
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_STOPPED);
		// 계좌 잔액이 적은 경우
		if(account.getBalance() <amount)
			throw  BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
	}
}
