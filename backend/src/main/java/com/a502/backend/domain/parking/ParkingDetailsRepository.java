package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.ParkingDetail;
import com.a502.backend.domain.parking.response.ParkingDetailList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface ParkingDetailsRepository extends JpaRepository<ParkingDetail, Integer> {
    ParkingDetail findTopByParkingOrderByCreatedAtDesc(Parking parking);

    List<ParkingDetail> findAllByParkingOrderByCreatedAtDesc(Parking parking);
}
