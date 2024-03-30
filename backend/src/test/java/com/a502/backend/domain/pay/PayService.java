//package com.a502.backend.domain.pay;
//
//import com.a502.backend.application.facade.PayFacade;
//import com.a502.backend.domain.account.AccountService;
//import com.a502.backend.domain.payment.request.TransferMoneyRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class PayService {
//	@Autowired
//	AccountService accountService;
//
//	@Autowired
//	PayFacade payFacade;
//
//	@Test
//	public void transferMoneyTest() throws InterruptedException {
//		// given
//		int numThreads = 10;
//		String accountNumberIn = "5021-8936-71-8430";
//		String accountNumberOut = "5021-0684-07-0684";
//		int amount = 1;
//		String transType = "ADT004";
//		int accountInBalance = accountService.findByAccountNumber(accountNumberIn).getBalance();
//		int accountOutBalance = accountService.findByAccountNumber(accountNumberOut).getBalance();
//		TransferMoneyRequest req = TransferMoneyRequest.builder()
//				.accountNumberIn(accountNumberIn)
//				.accountNumberOut(accountNumberOut)
//				.amount(amount)
//				.transType(transType)
//				.build();
//		// when
//		CountDownLatch doneSignal = new CountDownLatch(numThreads);
//		ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
//		AtomicInteger count = new AtomicInteger(0);
//		for (int i = 0; i < numThreads; i++) {
//			System.out.println("!!!!!!!!!!!!");
//			System.out.println(accountService.findByAccountNumber(accountNumberIn).getBalance());
//			count.incrementAndGet();
//			executorService.execute(() -> {
//				payFacade.transferMoney(req);
//				doneSignal.countDown();
//			});
//		}
//		doneSignal.await();
//		executorService.shutdown();
//		// then
//		assertAll(
//				()->assertEquals(accountInBalance + numThreads * amount, accountService.findByAccountNumber(accountNumberIn).getBalance()),
//				()->assertEquals(accountOutBalance - numThreads * amount, accountService.findByAccountNumber(accountNumberOut).getBalance())
//		);
//	}
//}
