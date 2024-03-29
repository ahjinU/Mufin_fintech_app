package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.*;
import com.a502.backend.application.facade.ParkingFacade;
import com.a502.backend.application.facade.StockFacade;
import com.a502.backend.domain.stock.StockHoldingsId;
import com.a502.backend.domain.stock.StockHoldingsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.domain.stock.response.TotalStockList;
import com.a502.backend.domain.stock.response.TotalStockListResponse;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

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

    public void saveParkingAccount(Parking newParkingAccount) {
        parkingRepository.save(newParkingAccount);
    }

    @Transactional
    public Parking createParkingAccount(User user) {

        Parking newParkingAccount = Parking.builder()
                .user(user)
                .interest(0.2)
                .balance(100000)
                .build();

        System.out.println("파킹통장 생성=-=====");

         return parkingRepository.save(newParkingAccount);
    }

}
