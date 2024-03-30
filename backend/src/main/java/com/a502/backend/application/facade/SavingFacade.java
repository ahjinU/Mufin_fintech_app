package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.Savings;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.savings.Request.*;
import com.a502.backend.domain.savings.Response.*;
import com.a502.backend.domain.savings.SavingsService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SavingFacade {
	private final SavingsService savingsService;
	private final UserService userService;
	private final AccountService accountService;
	private final AccountDetailService accountDetailService;
	private final CodeService codeService;

	public void registerSavings(RegisterSavingsRequest registerSavingsRequest) {
		User parents = userService.userFindByEmail();
		String name = registerSavingsRequest.getName();
		int period = registerSavingsRequest.getPeriod();
		double interest = registerSavingsRequest.getInterest();
		// 부모만 저장 가능
		if (parents.getParent() != null)
			throw BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION);
		Savings savings = Savings.builder()
				.interest(interest)
				.period(period)
				.name(name)
				.parent(parents)
				.build();

		savingsService.save(savings);
	}

	@Transactional
	public void deleteSavings(SavingsUuidRequest savingsUuidRequest) {
		String savingsUuid = savingsUuidRequest.getSavingsUuid();

		User parents = userService.userFindByEmail();
		Savings savings = savingsService.findByUuid(savingsUuid);
		// 부모 본인만 삭제 가능
		if (savings.getParent() != parents)
			throw BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION);
		// 자식들이 해당 적금 상품에 가입이 되어 있는 경우
		List<Account> accountList = accountService.findAllSavingsBySaving(savings);
		if (!accountList.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_SAVINGS_DELETE);
		// 삭제
		savings.setDeleted(true);
	}

	public AllSavingsProductResponse getAllSavingProduct() {
		User child = userService.userFindByEmail();
		User parents = child.getParent();
		List<Savings> savingsList = savingsService.findAllByParents(parents);
		List<SavingsDetail> detail = new ArrayList<>();
		for (Savings s : savingsList) {
			SavingsDetail savingsDetail = SavingsDetail.builder()
					.savingsUuid(s.getSavingUuid().toString())
					.interest(s.getInterest())
					.period(s.getPeriod())
					.name(s.getName())
					.createdAt(s.getCreatedAt())
					.build();
			detail.add(savingsDetail);
		}
		return AllSavingsProductResponse.builder()
				.savingsList(detail)
				.build();
	}

	public SavingsDetail getSavingsProduct(SavingsUuidRequest savingsUuidRequest) {
		String savingsUuid = savingsUuidRequest.getSavingsUuid();

		Savings savings = savingsService.findByUuid(savingsUuid);
		return SavingsDetail.builder()
				.savingsUuid(savingsUuid)
				.interest(savings.getInterest())
				.period(savings.getPeriod())
				.name(savings.getName())
				.createdAt(savings.getCreatedAt())
				.build();
	}

	@Transactional
	public void joinSavings(JoinSavingsRequest joinSavingsRequest) {
		String savingUuid = joinSavingsRequest.getSavingsUuid();
		int paymentAmount = joinSavingsRequest.getPaymentAmount();
		int paymentDate = joinSavingsRequest.getPaymentDate();
		String password = joinSavingsRequest.getPassword();

		Savings savings = savingsService.findByUuid(savingUuid);
		// 계좌 테이블 추가
		accountService.createSavingsAccount(savings, 0, paymentDate, paymentAmount, password);
	}

	public MyChildSavingsListResponse getMyChildSavings() {
		User parents = userService.userFindByEmail();
		List<User> kids = userService.findMyKidsByParents(parents);
		List<SavingsDetailAboutChild> savingsDetailAboutChildList = new ArrayList<>();
		for (User u : kids) {
			// 아이별 적금 리스트
			List<Account> savingsList = accountService.findAllSavingsByChild(u);
			List<SavingsDetailList> savingsDetailLists = new ArrayList<>();
			// 적금 하나 하나
			if (!savingsList.isEmpty()) {
				for (Account a : savingsList) {
					SavingsDetailList savingsDetail = SavingsDetailList.builder()
							.createdAt(a.getCreatedAt())
							.expiredAt(a.getCreatedAt().plusMonths(a.getSavings().getPeriod()))
							.period(a.getSavings().getPeriod())
							.interest(a.getSavings().getInterest())
							.paymentAmount(a.getPaymentAmount())
							.interestAmount(a.getInterestAmount())
							.state(a.getStatusCode().getName())
							.balance(a.getBalance())
							.build();
					savingsDetailLists.add(savingsDetail);
				}
				SavingsDetailAboutChild result = SavingsDetailAboutChild.builder()
						.userName(u.getName())
						.savingsDetailList(savingsDetailLists)
						.build();
				savingsDetailAboutChildList.add(result);
			}
		}

		if (savingsDetailAboutChildList.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_SAVINGS_NOT_EXIST_MY_CHILD);
		return MyChildSavingsListResponse.builder()
				.savingsDetailListByChild(savingsDetailAboutChildList)
				.build();
	}

	@Transactional
	public void depositToSavings(DepositSavingsRequest depositSavingsRequest) {
		User user = userService.userFindByEmail();
		String password = depositSavingsRequest.getPassword();
		// 적금 계좌
		String accountUuid = depositSavingsRequest.getAccountUuid();
		// 적금계좌
		Account savingAccount = accountService.findByAccountUuid(accountUuid);
		// 입출금 계좌
		Account account = accountService.findByUser(user);
		int cnt = depositSavingsRequest.getCnt();
		int totalAmount = savingAccount.getPaymentAmount() * cnt;
		// 비밀번호 확인

		// 출금 계좌 잔액 확인
		if (account.getBalance() < totalAmount)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
		// 입출금 출금 처리
		account.updateAccount(account.getBalance() - totalAmount);
		// 적금 계좌 입금
		savingAccount.depositSavings(cnt, totalAmount);
		// 거래 내역 처리
		accountDetailService.save(AccountDetail.builder()
				.amount(-totalAmount)
				.balance(account.getBalance() - totalAmount)
				.counterpartyName("적금 불입(" + savingAccount.getPaymentCycle() + "/" + savingAccount.getSavings().getPeriod() + ")")
				.counterpartyAccount(savingAccount.getAccountNumber())
				.account(account)
				.accountDetailTypeCode(codeService.findByName("적금이체"))
				.accountDetailStatusCode(codeService.findByName("거래완료"))
				.build());
		accountDetailService.save(AccountDetail.builder()
				.amount(totalAmount)
				.balance(savingAccount.getBalance() + totalAmount)
				.counterpartyName("적금 입금(" + savingAccount.getPaymentCycle() + "/" + savingAccount.getSavings().getPeriod() + ")")
				.counterpartyAccount(account.getAccountNumber())
				.account(savingAccount)
				.accountDetailTypeCode(codeService.findByName("적금이체"))
				.accountDetailStatusCode(codeService.findByName("거래완료"))
				.build());
	}

	public void cancelSavings(CancelSavingsRequest cancelSavingsRequest) {
		String accountUuid = cancelSavingsRequest.getAccountUuid();
//		String password = cancelSavingsRequest.getPassword();
		User user = userService.userFindByEmail();
		Account savingsAccount = accountService.findByAccountUuid(accountUuid);
		String savingsAccountNum = savingsAccount.getAccountNumber();
		int amount = savingsAccount.getBalance();
		// 적금 해지처리
		savingsAccount.cancelSavings(codeService.findStatusCode("해지"));
		// 입출금 계좌로 돈 송금
		Account account = accountService.findByUser(user);
		account.updateAccount(account.getBalance() + amount);
		// 거래내역 업데이트
		accountDetailService.save(AccountDetail.builder()
				.amount(amount)
				.balance(account.getBalance())
				.counterpartyName("적금 중도 해지")
				.counterpartyAccount(savingsAccountNum)
				.account(account)
				.accountDetailTypeCode(codeService.findTypeCode("적금이체"))
				.accountDetailStatusCode(codeService.findStatusCode("거래완료"))
				.build());
	}

	public void terminateSavings(CancelSavingsRequest cancelSavingsRequest) {
		User user = userService.userFindByEmail();
		User parents = user.getParent();
		String accountUuid = cancelSavingsRequest.getAccountUuid();
		Account parentsAccount = accountService.findByUser(parents);
		Account childAccount = accountService.findByUser(user);
		Account savingsAccount = accountService.findExpiredSavingsAccountByUuid(accountUuid);
		int originalAmount = savingsAccount.getBalance();
		// 이자 수령액 계산하기 (이자 받을 수 있는 금액 제한하기)
		List<AccountDetail> savingsDetail = accountDetailService.findSavingsAccountDetail(savingsAccount);
		// 기간 계산해서 이자 계산하기
		int interest = 0;
		// 이자율
		double interestRatio = savingsAccount.getSavings().getInterest();
		// 오늘 날짜
		LocalDate today = LocalDate.now();
		for (AccountDetail ad : savingsDetail) {
			// 적금 불입 날짜
			LocalDate depositDate = ad.getCreatedAt().toLocalDate();
			// 이자 계산 기간(일)
			int interestPeriod = Period.between(today, depositDate).getDays();
			// 불입 금액
			int amount = ad.getAmount();
			// 이자 계산
			interest += (int) (amount * (interestPeriod / 365) * (interestRatio / 100));
		}
		// 이자 최대 금액
		int maxLimitInterest = (int) (savingsAccount.getPaymentAmount() * (interestRatio / 100) * ((31 + 59 + 90 + 120 + 151 + 181 + 212 + 243 + 273 + 304 + 334 + 365) / 365));

		if (interest > maxLimitInterest)
			interest = maxLimitInterest;
		// 이자 금액만큼 부모님 입출금 계좌에 존재하는지 체크 (없으면 해지 불가, 있으면 해지 가능)
		if (parentsAccount.getBalance() < interest)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
		// 계좌 테이블(자식,부모) 업데이트 (삭제 여부, 이자 수령액, ....)
		savingsAccount.terminateSavings(interest, codeService.findStatusCode("해지"));
		parentsAccount.updateAccount(-interest);
		childAccount.updateAccount(interest + savingsAccount.getBalance());
		// 거래 내역 테이블(자식, 부모) 해당 이자 부모님 계좌에서 자식 입출금 계좌로 넣기
		accountDetailService.save(AccountDetail.builder()
				.amount(-originalAmount)
				.balance(0)
				.counterpartyAccount(childAccount.getAccountNumber())
				.counterpartyName("적금 만기 해지")
				.account(childAccount)
				.accountDetailTypeCode(codeService.findTypeCode("적금이체"))
				.accountDetailStatusCode(codeService.findStatusCode("거래완료"))
				.build());
		accountDetailService.save(AccountDetail.builder()
				.amount(-interest)
				.balance(parentsAccount.getBalance())
				.counterpartyAccount(childAccount.getAccountNumber())
				.counterpartyName("적금 이자 지급(" + user.getName() + ")")
				.account(parentsAccount)
				.accountDetailTypeCode(codeService.findTypeCode("적금이체"))
				.accountDetailStatusCode(codeService.findStatusCode("거래완료"))
				.build());
		accountDetailService.save(AccountDetail.builder()
				.amount(interest+savingsAccount.getBalance())
				.balance(childAccount.getBalance())
				.counterpartyAccount(parentsAccount.getAccountNumber())
				.counterpartyName("적금 이자")
				.account(childAccount)
				.accountDetailTypeCode(codeService.findTypeCode("적금이체"))
				.accountDetailStatusCode(codeService.findStatusCode("거래완료"))
				.build());
	}

	public MyAllSavingsResponse getMyAllSavings() {
		User user = userService.userFindByEmail();
		List<Account> savingsAccountList = accountService.findAllSavingsByChild(user);
		List<MySavings> myAllSavings = new ArrayList<>();
		for (Account a : savingsAccountList) {
			LocalDate today = LocalDate.now();
			// 생성 월
			int createdDay = a.getCreatedAt().getDayOfMonth();
			// 생성 일
			int createdMonth = a.getCreatedAt().getMonthValue();
			// 만기일
			LocalDateTime expiredAt = a.getCreatedAt().plusMonths(a.getSavings().getPeriod());
			// 납부 시작일
			LocalDateTime startedAt;
			// 다음달부터 내는 경우
			if (createdDay > a.getPaymentDate()) {
				if (createdMonth + 1 > 12) {
					startedAt = a.getCreatedAt().withMonth(createdMonth - 11).withYear(a.getCreatedAt().getYear()).withDayOfMonth(a.getPaymentDate());
				} else {
					startedAt = a.getCreatedAt().withMonth(createdMonth + 1).withDayOfMonth(a.getPaymentDate());
				}
				// 이번달부터 내는 경우
			} else if (createdMonth < a.getPaymentDate()) {
				startedAt = a.getCreatedAt().withDayOfMonth(a.getPaymentDate());
				// 가입일에 내는 경우
			} else {
				startedAt = a.getCreatedAt();
			}
			Period period = Period.between(startedAt.toLocalDate(), today);
			//
			int overdueCnt = 0;
			if (period.getMonths() + 1 > a.getPaymentCycle()) {
				overdueCnt = period.getMonths() + 1 - a.getPaymentCycle();
			}
			MySavings savings = MySavings.builder()
					.accountNumber(a.getAccountNumber())
					.accountUuid(a.getAccountUuid().toString())
					.balance(a.getBalance())
					.state(a.getStatusCode().getName())
					.paymentAmount(a.getPaymentAmount())
					.paymentDate(a.getPaymentDate())
					.paymentCycle(a.getPaymentCycle())
					.createdAt(a.getCreatedAt())
					.savingsInterest(a.getSavings().getInterest())
					.savingsPeriod(a.getSavings().getPeriod())
					.savingsName(a.getSavings().getName())
					.expiredAt(expiredAt)
					.overdueCnt(overdueCnt)
					.build();
			myAllSavings.add(savings);
		}
		return MyAllSavingsResponse.builder()
				.savingsList(myAllSavings)
				.build();
	}

	public MySavings getMySavingsDetail(CancelSavingsRequest accountUUidRequest) {
		String accountUuid = accountUUidRequest.getAccountUuid();
		Account savingsAccount = accountService.findByAccountUuid(accountUuid);
		User user = userService.userFindByEmail();

		LocalDate today = LocalDate.now();
		// 생성 월
		int createdDay = savingsAccount.getCreatedAt().getDayOfMonth();
		// 생성 일
		int createdMonth = savingsAccount.getCreatedAt().getMonthValue();
		// 만기일
		LocalDateTime expiredAt = savingsAccount.getCreatedAt().plusMonths(savingsAccount.getSavings().getPeriod());
		// 납부 시작일
		LocalDateTime startedAt;
		// 다음달부터 내는 경우
		if (createdDay > savingsAccount.getPaymentDate()) {
			if (createdMonth + 1 > 12) {
				startedAt = savingsAccount.getCreatedAt().withMonth(createdMonth - 11).withYear(savingsAccount.getCreatedAt().getYear()).withDayOfMonth(savingsAccount.getPaymentDate());
			} else {
				startedAt = savingsAccount.getCreatedAt().withMonth(createdMonth + 1).withDayOfMonth(savingsAccount.getPaymentDate());
			}
			// 이번달부터 내는 경우
		} else if (createdMonth < savingsAccount.getPaymentDate()) {
			startedAt = savingsAccount.getCreatedAt().withDayOfMonth(savingsAccount.getPaymentDate());
			// 가입일에 내는 경우
		} else {
			startedAt = savingsAccount.getCreatedAt();
		}
		Period period = Period.between(startedAt.toLocalDate(), today);
		//
		int overdueCnt = 0;
		if (period.getMonths() + 1 > savingsAccount.getPaymentCycle()) {
			overdueCnt = period.getMonths() + 1 - savingsAccount.getPaymentCycle();
		}
		MySavings savings = MySavings.builder()
				.accountNumber(savingsAccount.getAccountNumber())
				.accountUuid(savingsAccount.getAccountUuid().toString())
				.balance(savingsAccount.getBalance())
				.state(savingsAccount.getStatusCode().getName())
				.paymentAmount(savingsAccount.getPaymentAmount())
				.paymentDate(savingsAccount.getPaymentDate())
				.paymentCycle(savingsAccount.getPaymentCycle())
				.createdAt(savingsAccount.getCreatedAt())
				.savingsInterest(savingsAccount.getSavings().getInterest())
				.savingsPeriod(savingsAccount.getSavings().getPeriod())
				.savingsName(savingsAccount.getSavings().getName())
				.expiredAt(expiredAt)
				.overdueCnt(overdueCnt)
				.build();
		return savings;
	}
}
