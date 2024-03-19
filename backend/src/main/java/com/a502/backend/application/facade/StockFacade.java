package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        StockBuy stockBuy = stockBuysService.save(price, cnt_total, stock, user);
        List<StockSell> list = stockSellsService.findTransactionList(stock, price);

        if (list == null) return;

        for(StockSell stockSell : list){
            if (cnt_total == 0) break;
            cnt_total -= transaction(stockBuy, stockSell);
        }
    }

    public void enter(String name){
        // db에서 요청 받은 이름으로 주식 정보 조회
        Stock stock = stocksService.findByName(name);
        // 주식 id
        int id = stock.getId();
        // 주식 id에 해당하는 매수 주문 리스트 조회
        List<StockBuy> sellList = stockBuysService.getBuyList(id);
        // 주식 id에 해당하는 매도 주문 리스트 조회
        List<StockSell> buyList = stockSellsService.getSellList(id);

    }



    /**
     * 매도-매수 거래, 거래 이후 정보 수정 메서드
     * @param stockBuy 매수 거래
     * @param stockSell 매도 거래
     * @return 최종 거래 개수
     */
    @Transactional
    public int transaction(StockBuy stockBuy, StockSell stockSell){
        int transCnt = Math.min(stockBuy.getCntNot(), stockSell.getCntNot());
        /**
         * 1. parking Detail :  파킹통장 거래내역 추가
         * 2. Stock Detail : 해당 주식 가격 변경
         * 3. StockSells / StockBuys : 해당 거래 ID 수정 ( cnt 만큼 차감 후 상태 변환 )
         * 4. StockHoldings ( seller / buyer ) : 보유 주식 수정
         * 5. WebSocket 연결 이후 주식 값 update 해주기
         */
        return transCnt;
    }

    @Transactional
    public void stockSell(int userId, String name, int price, int cnt_total){
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        stockDetailsService.validStockPrice(stock, price);
        stockHoldingsService.validStockHolding(user, stock, cnt_total);



    }
}
