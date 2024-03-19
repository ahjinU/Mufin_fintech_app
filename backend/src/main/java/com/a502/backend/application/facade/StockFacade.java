package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.stock.StockBuysService;
import com.a502.backend.domain.stock.StockSellsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StockFacade {

    private final UserService userService;
    private final StocksService stocksService;
    private final StockBuysService stockBuysService;
    private final StockSellsService stockSellsService;
    private final ParkingService parkingService;
    private final StockDetailsService stockDetailsService;
    private final StockHoldingsService stockHoldingsService;
    private final ParkingDetailsService parkingDetailsService;


    /**
     * 매수 거래 신청 메서드
     * @param userId 매수 신청한 userId
     * @param name 주식 이름
     * @param price 주식 가격
     * @param cnt_total 주식 개수
     */
    @Transactional
    public void stockBuy(int userId, String name, int price, int cnt_total){
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        parkingService.validParkingBalance(user, price * cnt_total);

        StockBuy stockBuy = stockBuysService.save(user, stock, price, cnt_total);
        List<StockSell> list = stockSellsService.findTransactionList(stock, price);

        if (list == null) return;
        for(StockSell stockSell : list){
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
        }

        stockDetailsService.updateStockDetail(stock, price);
    }

    /**
     * 매도 거래 신청 메서드
     * @param userId 매수 신청한 userId
     * @param name 주식 이름
     * @param price 주식 가격
     * @param cnt_total 주식 개수
     */
    @Transactional
    public void stockSell(int userId, String name, int price, int cnt_total){
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        stockHoldingsService.validStockHolding(user, stock, cnt_total);

        StockSell stockSell = stockSellsService.save(user, stock, price, cnt_total);
        List<StockBuy> list = stockBuysService.findTransactionList(stock, price);

        if (list == null) return;
        for(StockBuy stockBuy : list){
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
     * @param stockBuy 매수 거래
     * @param stockSell 매도 거래
     * @return 최종 거래 개수
     */
    @Transactional
    public int transaction(StockBuy stockBuy, StockSell stockSell){
        int transCnt = Math.min(stockBuy.getCntNot(), stockSell.getCntNot());

        ParkingDetail detailSell = parkingDetailsService.saveStockSell(stockSell, parkingService.findByUser(stockSell.getUser()), transCnt);
        ParkingDetail detailBuy = parkingDetailsService.saveStockBuy(stockBuy, parkingService.findByUser(stockBuy.getUser()), transCnt);

        parkingService.updateParkingBalance(stockSell.getUser(), detailSell.getBalance());
        parkingService.updateParkingBalance(stockBuy.getUser(), detailBuy.getBalance());

        stockSellsService.stockSell(stockSell, transCnt);
        stockBuysService.stockBuy(stockBuy, transCnt);

        stockHoldingsService.stockSell(stockSell.getUser(), stockSell.getStock(), transCnt, stockSell.getPrice());
        stockHoldingsService.stockBuy(stockBuy.getUser(), stockBuy.getStock(), transCnt, stockBuy.getPrice());
        return transCnt;
    }



    // 주가 실시간 조회
    public List<StockOrderList> enter(String name){
        // db에서 요청 받은 이름으로 주식 정보 조회
        Stock stock = stocksService.findByName(name);
        // 주식 id
        int id = stock.getId();
        // 매도 / 매수 주문 리스트
        List<StockOrderList> stockOrderList = new ArrayList<>();
        // 주식 id에 해당하는 매수 주문 리스트 조회
        List<StockBuy> sellList = stockBuysService.getBuyList(id);
        for(int i = 0 ; i<sellList.size(); i++){
            StockOrderList sellOrder = new StockOrderList();
            sellOrder.builder().sellOrderCnt(sellList.get(i).getCntNot()).price(sellList.get(i).getPrice());
            stockOrderList.add(sellOrder);
        }
        // 주식 id에 해당하는 매도 주문 리스트 조회
        List<StockSell> buyList = stockSellsService.getSellList(id);
        for(int i = 0 ; i<buyList.size(); i++){
            StockOrderList buyOrder = new StockOrderList();
            buyOrder.builder().buyOrderCnt(buyList.get(i).getCntNot()).price(buyList.get(i).getPrice());
            stockOrderList.add(buyOrder);
        }
        return stockOrderList;
    }
}
