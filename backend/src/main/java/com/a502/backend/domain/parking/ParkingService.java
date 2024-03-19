package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Parking findById(int id){
        return parkingRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_EXIST));
    }

    public Parking findByUser(User user){
        return parkingRepository.findByUser(user).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_EXIST));
    }

    @Transactional
    public void validParkingBalance(User user, int balance){
        Parking parking = findByUser(user);
        if (parking.getBalance() < balance)
            throw BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_ENOUGH_BALANCE);
    }

    @Transactional
    public void updateParkingBalance(User user, int balance){
        Parking parking = findByUser(user);
        parking.setBalance(balance);
    }
}
