//package com.a502.backend.application.facade;
//
//import com.a502.backend.application.entity.*;
//import com.a502.backend.domain.account.AccountDetailService;
//import com.a502.backend.domain.account.AccountService;
//import com.a502.backend.domain.account.CashDetailService;
//import com.a502.backend.domain.allowance.request.AllowanceByMonthRequest;
//import com.a502.backend.domain.allowance.response.AllowanceByMonthResponse;
//import com.a502.backend.domain.allowance.response.AllowanceListByMonth;
//import com.a502.backend.domain.allowance.response.OutcomeDetail;
//import com.a502.backend.domain.loan.LoansService;
//import com.a502.backend.domain.user.UserService;
//import com.a502.backend.global.code.CodeService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.YearMonth;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//@Transactional
//public class AllowanceFacade {
//	private final AccountDetailService accountDetailService;
//	private final AccountService accountService;
//	private final UserService userService;
//	private final CodeService codeService;
//	private final CashDetailService cashDetailService;
//	private final LoansService loansService;
//
//	public AllowanceByMonthResponse getAllowanceByMonth(AllowanceByMonthRequest allowanceByMonthRequest) {
//		int year = allowanceByMonthRequest.getYear();
//		int month = allowanceByMonthRequest.getMonth();
//		// 시작일
//		LocalDateTime startDay = YearMonth.of(year, month).atDay(1).atStartOfDay();
//		// 종료일
//		LocalDateTime endDay = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59);
//
//		String childUuid = allowanceByMonthRequest.getChildUuid();
//		Code typeCodeDW = codeService.findTypeCode("입출금");
//		Code typeCodeS = codeService.findTypeCode("적금");
//		Code statusCode = codeService.findStatusCode("정상");
//		User user = null;
//		// 유저 아이디로 계좌 찾기
//		if (childUuid == null) {
//			user = userService.userFindByEmail();
//		} else {
//			user = userService.findByUserUuid(childUuid);
//		}
//		List<Account> account = accountService.getAccountByCondition(user, typeCodeDW, statusCode);
//		// 기간별 계좌 거래 내역
//		List<AccountDetail> accountDetail = accountDetailService.getAllAccountDetailsByAccountAndPeriod(account.get(0), startDay, endDay);
//		// 기간별 현금 거래 내역
//		List<CashDetail> cashDetailList = cashDetailService.getAllCashDetailsByUserAndPeriod(user, startDay, endDay);
//		// 대출 내역 조회
//		List<Loan> loanList = loansService.findByChild(user.getId());
//		// 적금 내역 조회
//		List<Account> savingList = accountService.getAccountByCondition(user, typeCodeS, statusCode);
//
//
//		List<AllowanceListByMonth> dayDetailList = new ArrayList<>();
//		List<Integer> loanDay = new ArrayList<>();
//		List<Integer> savingDay = new ArrayList<>();
//		int incomeMonth = 0;
//		int outcomeMonth = 0;
//
//		int day;
//		int incomeDay;
//		int outcomeDay;
//		List<OutcomeDetail> details = new ArrayList<>();
//
//		String storeName;
//		String type;
//		String accountDetailUuid;
//		int amount;
//		String category;
//
//		// 계좌 거래 내역 순회
//		for (int i = 0; i < accountDetail.size(); i++) {
//			// 일
//			day = accountDetail.get(i).getCreatedAt().getDayOfMonth();
//			// 총 지출, 수입(월별)
//			if (accountDetail.get(i).getAmount() > 0) {
//				incomeMonth += accountDetail.get(i).getAmount();
//			} else if(accountDetail.get(i).getAmount()<0){
//				outcomeMonth -= accountDetail.get(i).getAmount();
//			}
//		}
//		// 현금 거래 내역 순회
//
//		// loanDay 값 할당
//		for (Loan l : loanList) {
//			loanDay.add(l.getPaymentDate());
//		}
//		// savingDay 값 할당
//		for (Account a : savingList) {
//			savingDay.add(a.getPaymentDate());
//		}
//		// incomeMonth 값 할당
//		for (int i = 0; i < accountDetail.size(); i++) {
//			int transAmount = accountDetail.get(i).getAmount();
//			// 지출
//			if (transAmount < 0) {
//
//			}
//		}
//		// outcomeMonth 값 할당
//		for (int i = 0; i < cashDetailList.size(); i++) {
//		}
//		OutcomeDetail.builder()
//				.storeName()
//				.type()
//				.accountDetailUuid()
//				.amount()
//				.category()
//				.build();
//
//		AllowanceListByMonth.builder()
//				.day()
//				.incomeDay()
//				.outcomeDay()
//				.details()
//				.build();
//
//		// 조합하기
//		AllowanceByMonthResponse.builder()
//				.dayDetailList()
//				.incomeMonth(incomeMonth)
//				.outcomeMonth(outcomeMonth)
//				.loanDay(loanDay)
//				.savingDay(savingDay)
//				.build();
//	}
//}
