package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ParkingDetailsService {
    private final ParkingDetailsRepository parkingDetailsRepository;

    public void stockBuy(User user, Stock stock, Parking parking, int cnt, int price){

    }

    public void stockSell(User user, Stock stock, Parking parking, int cnt, int price){

    }
}
