package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.StockSell;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.stock.StockBuysService;
import com.a502.backend.domain.stock.StockSellsService;
import com.a502.backend.domain.stock.StocksService;
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

    UserService userService;
    StocksService stocksService;
    StockBuysService stockBuysService;
    StockSellsService stockSellsService;



    @Transactional
    public void stockBuy(int userId, String name, int price, int cnt_total){
        User user = userService.findById(userId);
        Stock stock = stocksService.findByName(name);

        // parking 통장에 유효한 돈이 있는지 체크


        // stock buy 주문 넣기
        stockBuysService.save(price, cnt_total, stock, user);

        // 주문 넣은 후 거래 할 수 있는 매도 주문이 있는지 확인

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


    @Transactional
    public void transactionBuy(StockBuy stockBuy, StockSell stockSell){
        // 거래하기
        // stockSell 에서 주식 id, price 가 똑같은 사람들의 리스트를 가져온다.
        // 개수를 매칭하면서 거래를 성사 시킨다.
        // stock details : 변경 주식 가격 추가 하기
        // stock sell : 살 수 있는 주식 있는지 검사하고 있으면 거래 하기
        // parking 통장 거래내역 수정 ( 매도자/매수자 통장에 둘다 )
    }
}
