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

    public ParkingDetail saveStockBuy(StockBuy stockBuy, Parking parking, int cnt){
        ParkingDetail last = getLastDetail(parking);
        int amount = -stockBuy.getPrice() * cnt;
        return parkingDetailsRepository.save(ParkingDetail.builder()
                .parking(parking)
                .amount(amount)
                .balance(last.getBalance() + amount)
                .transCode(StockCode.PARKING_TRANSCODE_BUY)
                .memo("매수 " + stockBuy.getStock().getName() + " " + cnt + "주")
                .build()
        );
    }

    public ParkingDetail saveStockSell(StockSell stockSell, Parking parking, int cnt){
        ParkingDetail last = getLastDetail(parking);
        int amount = stockSell.getPrice() * cnt;
        return parkingDetailsRepository.save(ParkingDetail.builder()
                .parking(parking)
                .amount(amount)
                .balance(last.getBalance() + amount)
                .transCode(StockCode.PARKING_TRANSCODE_SELL)
                .memo("매도 " + stockSell.getStock().getName() + " " + cnt + "주")
                .build()
        );
    }
}
