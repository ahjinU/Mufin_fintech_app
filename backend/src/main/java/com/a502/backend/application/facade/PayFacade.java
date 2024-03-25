package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.payment.AccountDetailService;
import com.a502.backend.domain.payment.AccountService;
import com.a502.backend.domain.payment.request.MyAccount;
import com.a502.backend.domain.payment.request.RecipientAccount;
import com.a502.backend.domain.payment.request.TransferMoneyRequest;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PayFacade {
	private final AccountService accountService;
	private final AccountDetailService accountDetailService;
	private final CodeService codeService;

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
	public void checkMyAccount(MyAccount myAccount) {
		String accountNumberOut = myAccount.getAccountNumberOut();
		int amount = myAccount.getAmount();
		Account account = accountService.findByAccountNumber(accountNumberOut);
		// 계좌가 존재하지 않는 경우
		if (account.isDeleted())
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_NOT_EXIST);
		// 계좌가 정지상태인 경우
		if (account.getStatusCode().getName().equals("정지"))
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_STOPPED);
		// 계좌 잔액이 적은 경우
		AtomicInteger atomicInteger = new AtomicInteger(account.getBalance());
		if (atomicInteger.addAndGet(-amount) < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);

	}

	// 송금
	@Transactional
	public void transferMoney(TransferMoneyRequest transferMoneyRequest) {
		String accountNumberIn = transferMoneyRequest.getAccountNumberIn();
		String accountNumberOut = transferMoneyRequest.getAccountNumberOut();
		int amount = transferMoneyRequest.getAmount();
		String transType = transferMoneyRequest.getTransType();

		Account accountOut = accountService.findByAccountNumber(accountNumberOut);
		Account accountIn = accountService.findByAccountNumber(accountNumberIn);

		AtomicInteger balanceOut = new AtomicInteger(accountOut.getBalance());
		AtomicInteger balanceIn = new AtomicInteger(accountIn.getBalance());

		// 송금처리
		int afterBalanceOut = balanceOut.addAndGet(-amount);
		int afterBalanceIn = balanceIn.addAndGet(+amount);

		// 거래내역 저장
		// 송금 하는 사람
		accountDetailService.save(AccountDetail.builder()
				.account(accountOut)
				.amount(-amount)
				.balance(afterBalanceOut)
				.accountDetailStatusCode(codeService.findById("ADS001"))
				.accountDetailTypeCode(codeService.findById(transType))
				.counterpartyAccount(accountIn.getAccountNumber())
				.counterpartyName(accountIn.getUser().getName())
				.build());

		// 송금 받는 사람
		accountDetailService.save(AccountDetail.builder()
				.account(accountIn)
				.amount(amount)
				.balance(afterBalanceIn)
				.accountDetailStatusCode(codeService.findById("ADS001"))
				.accountDetailTypeCode(codeService.findById(transType))
				.counterpartyAccount(accountOut.getAccountNumber())
				.counterpartyName(accountOut.getUser().getName())
				.build());

	}
}
