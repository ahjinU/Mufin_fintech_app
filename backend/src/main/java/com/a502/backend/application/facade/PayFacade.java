package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.payment.request.MyAccount;
import com.a502.backend.domain.payment.request.PaymentRequest;
import com.a502.backend.domain.payment.request.RecipientAccount;
import com.a502.backend.domain.payment.request.TransferMoneyRequest;
import com.a502.backend.domain.user.UserService;
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
@RequiredArgsConstructor
public class PayFacade {
	private final AccountService accountService;
	private final AccountDetailService accountDetailService;
	private final CodeService codeService;
	private final UserService userService;

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

		// 송금처리
		int afterBalanceOut = accountOut.getBalance() - amount;
		// 트랜잭션 의논사항
		if (afterBalanceOut < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
		int afterBalanceIn = accountIn.getBalance() + amount;

		// 거래내역 저장
		accountOut.updateAccount(afterBalanceOut);
		accountIn.updateAccount(afterBalanceIn);

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

	public void requestPayment(PaymentRequest paymentRequest) {
		int amount = paymentRequest.getAmount();
		String counterpartyName = paymentRequest.getCounterpartyName();
		System.out.println(counterpartyName);
		User user = userService.userFindByEmail();

		Account account = accountService.findByUser(user);
		int balance = account.getBalance();
		if (balance < amount)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
		account.updateAccount(balance - amount);

		accountDetailService.save(AccountDetail.builder()
				.account(account)
				.counterpartyName(counterpartyName)
				.amount(-amount)
				.balance(account.getBalance())
				.accountDetailStatusCode(codeService.findStatusCode("거래완료"))
				.accountDetailTypeCode(codeService.findTypeCode("결제"))
				.build());
	}
}
