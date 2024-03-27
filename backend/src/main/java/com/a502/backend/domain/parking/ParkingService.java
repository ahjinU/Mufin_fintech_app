package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.stock.StockHoldingsId;
import com.a502.backend.domain.stock.StockHoldingsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.global.code.CodeService;
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
    private final ParkingDetailsRepository parkingDetailsRepository;
    private final CodeService codeService;





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
    public void validParkingBalance(User user, int balance) {
        Parking parking = findByUser(user);
        if (parking.getBalance() < balance)
            throw BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_ENOUGH_BALANCE);
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

        initSeadMoney(newParkingAccount);
        initStockHolding(user);
    }

    private void initStockHolding(User user) {

        List<Stock> stocks = stocksService.findAllList();

        stocks.forEach(stock -> {
            StockHolding stockHolding = StockHolding.builder()
                    .user(user)
                    .stock(stock)
                    .cnt(10)
                    .build();

            stockHoldingsService.save(stockHolding);

        });
    }

    private void initSeadMoney(Parking newParkingAccount) {
        Code initCode = codeService.findTypeCode("시드머니");
        String counterpartyName = "머핀";

        ParkingDetail initParkingDetail= ParkingDetail.builder()
                .parking(newParkingAccount)
                .balance(newParkingAccount.getBalance())
                .counterpartyName(counterpartyName)
                .code(initCode)
                .build();

        parkingDetailsRepository.save(initParkingDetail);
    }
}
