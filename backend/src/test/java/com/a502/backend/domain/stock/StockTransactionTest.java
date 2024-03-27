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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
        int threads = 10;
        StockTransactionRequest request1;

        // when
        ExecutorService service = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);



//        latch.await();
//        service.shutdown();



        // then
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
        User user = userService.userFindByEmail();
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        int price1 = parkingService.getParkingBalance(user, price * cnt_total);
        int price2 = stockBuysService.getStockBuyWaitingList(user, stock, codeService.findByName("거래중"));
//        log.info("price1 : {}" , price1);
//        log.info("price2 : {}" , price2);
//        log.info("cnt_total : {}" , cnt_total * price);
        if (price1 - price2 < price * cnt_total)
            throw BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_ENOUGH_BALANCE);

        Code code = codeService.findByName("거래중");

        StockBuy stockBuy = stockBuysService.save(user, stock, price, cnt_total, code);
//        log.info("stockBuy : {}", stockBuy.getStock());
        List<StockSell> list = stockSellsService.findTransactionList(stock, price);
//        log.info("list : {}", list.size());

        if (list == null) return;
//        log.info("list : 반환됨.");
        for (StockSell stockSell : list) {
//            log.info("stockSell : {}", stockSell.getStock().getName());
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
        }

//        log.info("before: stockDetailService update");
        stockDetailsService.updateStockDetail(stock, price);
//        log.info("after: stockDetailService update");
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
        User user = userService.userFindByEmail();
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        int cnt1 = stockHoldingsService.getStockHolding(user, stock);
        int cnt2 = stockSellsService.getStockSellWaitingList(user, stock, codeService.findByName("거래중"));
//        log.info("cnt1 : {}" , cnt1);
//        log.info("cnt2 : {}" , cnt1);
//        log.info("cnt_total : {}" , cnt_total);

        if (cnt1 - cnt2 < cnt_total)
            throw BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST);

        Code code = codeService.findByName("거래중");
        StockSell stockSell = stockSellsService.save(user, stock, price, cnt_total, code);
        List<StockBuy> list = stockBuysService.findTransactionList(stock, price);

        if (list == null) return;
        for (StockBuy stockBuy : list) {
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
        }

        stockDetailsService.updateStockDetail(stock, price);
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
    @Transactional
    public int transaction(StockBuy stockBuy, StockSell stockSell) {
        int transCnt = Math.min(stockBuy.getCntNot(), stockSell.getCntNot());
        Code code = codeService.findByName("완료");

//        log.info("usersell : {}" ,stockSell.getUser().getName());
//        log.info("userBuy : {}" ,stockBuy.getUser().getName());
        ParkingDetail detailSell = parkingDetailsService.saveStockSell(stockSell, parkingService.findByUser(stockSell.getUser()), transCnt, codeService.findByName("매도"));
        ParkingDetail detailBuy = parkingDetailsService.saveStockBuy(stockBuy, parkingService.findByUser(stockBuy.getUser()), transCnt, codeService.findByName("매수"));

        parkingService.updateParkingBalance(stockSell.getUser(), detailSell.getBalance());
        parkingService.updateParkingBalance(stockBuy.getUser(), detailBuy.getBalance());


        stockSellsService.stockSell(stockSell, transCnt, code);
        stockBuysService.stockBuy(stockBuy, transCnt, code);

        stockHoldingsService.stockSell(stockSell.getUser(), stockSell.getStock(), transCnt, stockSell.getPrice());
        stockHoldingsService.stockBuy(stockBuy.getUser(), stockBuy.getStock(), transCnt, stockBuy.getPrice());
        return transCnt;
    }

}

/**
 * 매도 / 매수가 동시에 이루어질떄?
 *
 *
 */
