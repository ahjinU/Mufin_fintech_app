package com.a502.backend.domain.stock;


import com.a502.backend.application.entity.*;
import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.request.StockTransactionRequest;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
public class StockTransactionTest {

    @Autowired
    StocksService stocksService;
    @Autowired
    StockBuysService stockBuysService;
    @Autowired
    StockSellsService stockSellsService;
    @Autowired
    StockDetailsService stockDetailsService;
    @Autowired
    StockHoldingsService stockHoldingsService;
    @Autowired
    ParkingService parkingService;
    @Autowired
    ParkingDetailsService parkingDetailsService;
    @Autowired
    CodeService codeService;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("stock transaction Test")
    void stockTransactionTest() throws InterruptedException {
        // given
        String stockName = "바람개비";
        Stock stock = stocksService.findByName(stockName);
        int price = stockDetailsService.getLastDetail(stock).getPrice();
        int threads = 4;

        List<Integer> buyUserId = new ArrayList<>();
        List<StockTransactionRequest> selllist = new ArrayList<>();
        List<Integer> sellUserId = new ArrayList<>();
        List<StockTransactionRequest> buylist = new ArrayList<>();
        List<Integer> userList = new ArrayList<>();
        int[] p = {11, 12, 13};

        buylist.add(new StockTransactionRequest(stockName, price + 100, 4));
        buylist.add(new StockTransactionRequest(stockName, price + 200, 2));
        buylist.add(new StockTransactionRequest(stockName, price, 1));
        buylist.add(new StockTransactionRequest(stockName, price - 300, 1));

        buyUserId.add(p[0]);
        buyUserId.add(p[0]);
        buyUserId.add(p[0]);
        buyUserId.add(p[0]);

        selllist.add(new StockTransactionRequest(stockName, price + 100, 3));
        selllist.add(new StockTransactionRequest(stockName, price + 100, 3));
        selllist.add(new StockTransactionRequest(stockName, price + 100, 1));
        selllist.add(new StockTransactionRequest(stockName, price + 200, 1));

        sellUserId.add(p[1]);
        sellUserId.add(p[2]);
        sellUserId.add(p[1]);
        sellUserId.add(p[2]);

        userList.add(p[0]);
        userList.add(p[1]);
        userList.add(p[2]);

        List<User> users = setUser(userList);
        int beforeHolding = calStockHolding(users, stock);
        int beforeParking = calParkingBalance(users);

        log.info("-----------------------");
        log.info("stockName : {}" , stock.getName());
        log.info("beforeHolding : {}" , beforeHolding);
        log.info("beforeParking : {}" , beforeParking);
        log.info("-----------------------");

        // when
        ExecutorService service = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        AtomicInteger count = new AtomicInteger(0);
        count.intValue();
        for (int i = 0; i < threads; i++){
            int finalI = i;
            service.execute(() -> {
//                log.info("THREAD : {}" , finalI);
                stockBuy(buylist.get(finalI), buyUserId.get(finalI));
                stockSell(selllist.get(finalI), sellUserId.get(finalI));
                latch.countDown();
            });
            count.incrementAndGet();
        }

        latch.await();
        service.shutdown();

        // then

        List<User> finalUsers = users;
        assertAll(
                () -> assertTrue(calTransactionCnt(stock)),
                () -> assertTrue(calParkingDetail(finalUsers)),
                () -> assertEquals(beforeHolding, calStockHolding(finalUsers, stock)),
                () -> assertEquals(beforeParking, calParkingBalance(finalUsers))
        );
    }

    public int calStockHolding(List<User> userList, Stock stock){
        int result = 0;

        for(User user : userList){
            result += stockHoldingsService.getStockHolding(user, stock);
        }

        return result;
    }

    public int calParkingBalance(List<User> userList){
        int result = 0;

        for(User user : userList) {
            result += parkingService.getParkingBalance(user);
        }

        return result;
    }

    public boolean calParkingDetail(List<User> userList){
        for(User user : userList){
            Parking parking = parkingService.findByUser(user);
            ParkingDetail detail = parkingDetailsService.getLastDetail(parking);
//            log.info("parkingBalance: {}",parking.getBalance());
//            log.info("detailBalance: {}",detail.getBalance());
//            log.info("parkingBalanceID: {}",parking.getId());
//            log.info("detailBalanceID: {}",detail.getParking().getId());
            if (parking.getBalance() != detail.getBalance())
                return false;
        }
        return true;
    }

    public List<User> setUser(List<Integer> userList){
        List<User> list = new ArrayList<>();
        for (int userId : userList){
            User user = userService.findById(userId);
            list.add(user);
        }
        return list;
    }

    public boolean calTransactionCnt(Stock stock){
        List<StockBuy> buylist = stockBuysService.getStockTransListByStock(stock);
        List<StockSell> selllist = stockSellsService.getStockTransListByStock(stock);

        int buyCnt = 0;
        int sellCnt = 0;
        for(StockBuy buy : buylist){
            buyCnt += buy.getCntTotal() - buy.getCntNot();
        }
        for(StockSell sell : selllist){
            sellCnt += sell.getCntTotal() - sell.getCntNot();
        }
        if (sellCnt != buyCnt) return false;
        else return true;
    }


    /**
     * 매수 거래 신청 메서드
     *
     * @param request
     */
    @Transactional
    public void stockBuy(StockTransactionRequest request, int userId) {
        String name = request.getName();
        int price = request.getPrice();
        int cnt_total = request.getCnt_total();
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        int price1 = parkingService.getParkingBalance(user);
        int price2 = stockBuysService.getStockBuyWaitingList(user, stock, codeService.findByName("거래중"));
        if (price1 - price2 < price * cnt_total)
            throw BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_ENOUGH_BALANCE);

        log.info("거래 가능 상태 확인 완료!Buy");

        Code code = codeService.findByName("거래중");

        StockBuy stockBuy = stockBuysService.save(user, stock, price, cnt_total, code);
        List<StockSell> list = stockSellsService.findTransactionList(stock, price);

        if (list == null) return;
        for (StockSell stockSell : list) {
            log.info("StockSell list 값 검사 before : cntNot_{} cntTotal_{} getPrice_{} curTransactionNum{}", stockSell.getCntNot(), stockSell.getCntTotal(), stockSell.getPrice(), cnt_total);
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
            log.info("StockSell list 값 검사 after : cntNot_{} cntTotal_{} getPrice_{} curTransactionNum{}", stockSell.getCntNot(), stockSell.getCntTotal(), stockSell.getPrice(), cnt_total);

        }

//        stockDetailsService.updateStockDetail(stock, price);
    }

    /**
     * 매도 거래 신청 메서드
     *
     * @param request
     */
    @Transactional
    public void stockSell(StockTransactionRequest request, int userId) {
        String name = request.getName();
        int price = request.getPrice();
        int cnt_total = request.getCnt_total();
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        int cnt1 = stockHoldingsService.getStockHolding(user, stock);
        int cnt2 = stockSellsService.getStockSellWaitingList(user, stock, codeService.findByName("거래중"));

        if (cnt1 - cnt2 < cnt_total)
            throw BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST);

        log.info("거래 가능 상태 확인 완료!Sell");
        Code code = codeService.findByName("거래중");
        StockSell stockSell = stockSellsService.save(user, stock, price, cnt_total, code);
        List<StockBuy> list = stockBuysService.findTransactionList(stock, price);

        if (list == null) return;
        for (StockBuy stockBuy : list) {
            log.info("StockBuy list 값 검사 before : cntNot_{} cntTotal_{} getPrice_{} curTransactionNum{}", stockBuy.getCntNot(), stockBuy.getCntTotal(), stockBuy.getPrice(), cnt_total);
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
            log.info("StockBuy list 값 검사 after : cntNot_{} cntTotal_{} getPrice_{} curTransactionNum{}", stockBuy.getCntNot(), stockBuy.getCntTotal(), stockBuy.getPrice(), cnt_total);

        }

//        stockDetailsService.updateStockDetail(stock, price);
    }

    /**
     * 매도-매수 거래, 거래 이후 정보 수정 메서드
     * 1. update ParkingDetail : 파킹통장 거래내역 추가
     * 2. update ParkingBalance : 파킹통장 총 금액 수정
     * 3. update StockSell/StockBuy : 거래ID 에 대해 cntNot 값 수정
     * 4. update StockHodings : 매도인/매수인에 대해 보유 주식 수를 수정
     *
     * @param stockBuy  매수 거래
     * @param stockSell 매도 거래
     * @return 최종 거래 개수
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public int transaction(StockBuy stockBuy, StockSell stockSell) {
        int transCnt = Math.min(stockBuy.getCntNot(), stockSell.getCntNot());
        Code code = codeService.findByName("완료");

        log.info("stockBuy : {}" , stockBuy.getCntNot());
        log.info("stockSell : {}" , stockSell.getCntNot());

//        ParkingDetail detailSell = parkingDetailsService.saveStockSell(stockSell, parkingService.findByUser(stockSell.getUser()), transCnt, codeService.findByName("매도"));
//        ParkingDetail detailBuy = parkingDetailsService.saveStockBuy(stockBuy, parkingService.findByUser(stockBuy.getUser()), transCnt, codeService.findByName("매수"));

//        parkingService.updateParkingBalance(stockSell.getUser(), detailSell.getBalance());
//        parkingService.updateParkingBalance(stockBuy.getUser(), detailBuy.getBalance());

        stockSellsService.stockSell(stockSell, transCnt, code);
        stockBuysService.stockBuy(stockBuy, transCnt, code);

//        stockHoldingsService.stockSell(stockSell.getUser(), stockSell.getStock(), transCnt, stockSell.getPrice());
//        stockHoldingsService.stockBuy(stockBuy.getUser(), stockBuy.getStock(), transCnt, stockBuy.getPrice());
        log.info("transCnt : {}", transCnt);
        return transCnt;
    }

}

