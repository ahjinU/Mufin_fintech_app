package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockHolding;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.stock.StockHoldingsId;
import com.a502.backend.domain.stock.StockHoldingsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final StocksService stocksService;
    private final StockHoldingsService stockHoldingsService;



    public Parking findById(int id) {
        return parkingRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_EXIST));
    }

    public Parking findByUser(User user) {
        return parkingRepository.findByUser(user).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_EXIST));
    }

    @Transactional
    public List<Parking> findAllList(){
        return parkingRepository.findAll();
    }

    @Transactional
    public int getParkingBalance(User user, int balance) {
        Parking parking = findByUser(user);
        return parking.getBalance();
    }

    @Transactional
    public void updateParkingBalance(User user, int balance) {
        Parking parking = findByUser(user);
        parking.setBalance(balance);
    }

    public void createParkingAccount(User user) {
        Parking newParkingAccount = Parking.builder()
                .user(user)
                .interest(0.2)
                .balance(10000)
                .build();

        parkingRepository.save(newParkingAccount);

        // 모든 Stock 레코드 조회
        List<Stock> stocks = stocksService.findAllList();

        // 각 Stock에 대해 StockHolding 인스턴스 생성 및 저장
        stocks.forEach(stock -> {
            StockHolding stockHolding = StockHolding.builder()
                    .user(user)
                    .stock(stock)
                    .cnt(10)
                    .build();

            stockHoldingsService.save(stockHolding);
        });


    }
}
