package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.loan.LoanConversationService;
import com.a502.backend.domain.loan.LoanDetailService;
import com.a502.backend.domain.loan.LoanRefusalService;
import com.a502.backend.domain.loan.LoansService;
import com.a502.backend.domain.loan.Request.ApplyLoanRequest;
import com.a502.backend.domain.loan.Request.LoanUuidRequest;
import com.a502.backend.domain.loan.Request.RepayLoanRequest;
import com.a502.backend.domain.loan.Response.LoanDetailResponse;
import com.a502.backend.domain.loan.Response.LoanList;
import com.a502.backend.domain.loan.Response.LoanListForChildResponse;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanFacade {
	private final LoansService loansService;
	private final LoanRefusalService loanRefusalService;
	private final UserService userService;
	private final LoanConversationService loanConversationService;
	private final CodeService codeService;
	private final AccountService accountService;
	private final AccountDetailService accountDetailService;
	private final LoanDetailService loanDetailService;
	public void applyLoan(ApplyLoanRequest applyLoanRequest) {
		User child = userService.userFindByEmail();
		User parent = child.getParent();
		String reason = applyLoanRequest.getReason();
		int amount = applyLoanRequest.getAmount();
		int paymentTotalCnt = applyLoanRequest.getPaymentTotalCnt();
		int paymentDate = applyLoanRequest.getPaymentDate();
		String conversation = applyLoanRequest.getConversation();
		String penalty = applyLoanRequest.getPenalty();
		Code code = codeService.findStatusCode("심사중");
		// gpt 대화
		LoanConversation loanConversation = LoanConversation.builder()
				.content(conversation)
				.user(child)
				.build();

		loanConversationService.saveLoanConversation(loanConversation);
		LoanConversation loanConversationForLoan = loanConversationService.findByUserLast(child);
		// 대출
		Loan loan = Loan.builder()
				.reason(reason)
				.paymentTotalCnt(paymentTotalCnt)
				.paymentDate(paymentDate)
				.amount(amount)
				.penalty(penalty)
				.loanConversation(loanConversationForLoan)
				.child(child)
				.parent(parent)
				.code(code)
				.build();

		loansService.saveLoan(loan);
	}

	public LoanListForChildResponse getAllLoansForChild() {
		// 아이
		User child = userService.userFindByEmail();
		// 가입한 대출 전체 조회(상환된 것 제외)
		List<Loan> loans = loansService.getAllLoansForChild(child);
		// 결과값 담을 리스트
		List<LoanList> loansList = new ArrayList<>();
		for (Loan l : loans) {
			LoanRefusal loanRefusal = loanRefusalService.findByLoan(l);
			LoanList loanDetail = LoanList.builder()
					.reason(l.getReason())
					.loanUuid(String.valueOf(l.getLoanUuid()))
					.amount(l.getAmount())
					.paymentTotalCnt(l.getPaymentTotalCnt())
					.paymentNowCnt(l.getPaymentNowCnt())
					.status(codeService.findById(l.getCode().getId()).getName())
					.overDueCnt(l.getOverdueCnt())
					.build();
			if (loanRefusal != null) {
				loanDetail.updateLoanDetail(loanRefusal.getReason());
			}
			loansList.add(loanDetail);
		}
		return LoanListForChildResponse.builder().loansList(loansList).build();
	}

	public LoanDetailResponse getLoanDetailForChild(LoanUuidRequest loanUuidRequest) {

		String loanUuid = loanUuidRequest.getLoanUuid();

		Loan loan = loansService.findByUuid(loanUuid);

		int totalCnt = loan.getPaymentTotalCnt();

		LocalDate startDate = loan.getStartDate();
		LocalDate endDate = loan.getStartDate().plusMonths(totalCnt);
		Period period = Period.between(LocalDate.now(), endDate);
		String remainderDay = period.getMonths() + "개월 " + period.getDays() + "일";

		int amountByMonth = loan.getAmount() / loan.getPaymentTotalCnt();

		return LoanDetailResponse.builder()
				.reason(loan.getReason())
				.totalAmount(loan.getAmount())
				.remainderAmount(loan.getAmount() - loan.getPaymentNowCnt() * amountByMonth)
				.startDate(loan.getStartDate())
				.remainderDay(remainderDay)
				.paymentDate(loan.getPaymentDate())
				.overDueCnt(loan.getOverdueCnt())
				.build();
	}

	@Transactional
	public void repayLoan(RepayLoanRequest repayLoanRequest) {
		String loanUuid = repayLoanRequest.getLoanUuid();
		int paymentCnt = repayLoanRequest.getPayment_cnt();

		Loan loan = loansService.findByUuid(loanUuid);
		int payment = loan.getAmount() / loan.getPaymentTotalCnt();


		User user = userService.userFindByEmail();
		// 계좌
		Account account = accountService.findByUser(user);

		// 계좌 잔액이 적은 경우
		AtomicInteger atomicInteger = new AtomicInteger(account.getBalance());
		if (atomicInteger.addAndGet(-payment * paymentCnt) < 0)
			throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE);
		// 있다면 상환
		account.updateAccount(account.getBalance() - payment * paymentCnt);
		// 대출 상품 업데이트
		boolean isFinal = loan.repayLoan(paymentCnt);
		// 상환된 경우 업데이트
		if(isFinal)
			loan.completeLoan(codeService.findStatusCode("상환완료"));
		// 계좌 거래 내역 등록 //
		// 타입코드
		Code typeCode = codeService.findTypeCode("대출");
		// 상태코드
		Code statusCode = codeService.findStatusCode("거래완료");

		AccountDetail accountDetail = AccountDetail.builder()
				.amount(-payment * paymentCnt)
				.balance(account.getBalance())
				.counterpartyName("대출 상환  ("+ loan.getPaymentNowCnt()+"/"+loan.getPaymentTotalCnt()+")")
				.account(account)
				.accountDetailTypeCode(typeCode)
				.accountDetailStatusCode(statusCode)
				.build();
		accountDetailService.save(accountDetail);
		// 대출 납부 내역 등록(대출 , 거래내역 연결)
		LoanDetail loanDetail = LoanDetail.builder()
				.accountDetail(accountDetail)
				.loan(loan)
				.build();
		loanDetailService.save(loanDetail);
	}

}
