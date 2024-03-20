package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.*;
import com.a502.backend.global.code.StockCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ParkingDetailsService {
    private final ParkingDetailsRepository parkingDetailsRepository;

    public ParkingDetail getLastDetail(Parking parking){
        return parkingDetailsRepository.findTopByParkingOrderByCreatedAtDesc(parking);
    }

    public ParkingDetail saveStockBuy(StockBuy stockBuy, Parking parking, int cnt, Code code){
        ParkingDetail last = getLastDetail(parking);
        // 거래 주식회사 이름
        String counterpartyName = stockBuy.getStock().getName();
        int amount = -stockBuy.getPrice() * cnt;
        return parkingDetailsRepository.save(ParkingDetail.builder()
                .parking(parking)
                .amount(amount)
                .balance(last.getBalance() + amount)
                .counterpartyName(counterpartyName)
                .code(code)
                .build()
        );
    }

    public ParkingDetail saveStockSell(StockSell stockSell, Parking parking, int cnt, Code code){
        ParkingDetail last = getLastDetail(parking);
        // 거래 주식회사 이름
        String counterpartyName = stockSell.getStock().getName();
        int amount = stockSell.getPrice() * cnt;
        return parkingDetailsRepository.save(ParkingDetail.builder()
                .parking(parking)
                .amount(amount)
                .balance(last.getBalance() + amount)
                .counterpartyName(counterpartyName)
                .code(code)
                .build()
        );
    }
}
