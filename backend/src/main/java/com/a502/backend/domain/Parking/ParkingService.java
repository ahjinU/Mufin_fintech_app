package com.a502.backend.domain.Parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Parking findById(int id){
        return parkingRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_EXIST));
    }
}
