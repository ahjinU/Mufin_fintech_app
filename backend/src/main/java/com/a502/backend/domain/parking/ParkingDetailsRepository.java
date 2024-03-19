package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.ParkingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

interface ParkingDetailsRepository extends JpaRepository<ParkingDetail, Integer> {
}
