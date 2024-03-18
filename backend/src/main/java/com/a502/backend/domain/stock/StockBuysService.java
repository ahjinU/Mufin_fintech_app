package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockBuy;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockBuysService {
    private final StockBuysRepository stockBuysRepository;

    public StockBuy findById(int id){
        return stockBuysRepository.findById(id).orElseThrow(
                () -> BusinessException.of(ErrorCode.API_ERROR_STOCKBUY_NOT_EXIST));
    }

    @Transactional
    public void save(int price, int cntTotal, int status, Stock stock, User user){
        StockBuy stockBuy = stockBuysRepository.save(new StockBuy(price, cntTotal, status, stock, user));
        // stock details : 변경 주식 가격 추가 하기
        // stock sell : 살 수 있는 주식 있는지 검사하고 있으면 거래 하기
        //

    }

    @Transactional
    public void transactionBuy(StockBuy stockBuy){
        // 거래하기
        // stockSell 에서 주식 id, price 가 똑같은 사람들의 리스트를 가져온다.
        // 개수를 매칭하면서 거래를 성사 시킨다.
        // parking 통장 거래내역 수정 ( 매도자/매수자 통장에 둘다 )
    }
}
