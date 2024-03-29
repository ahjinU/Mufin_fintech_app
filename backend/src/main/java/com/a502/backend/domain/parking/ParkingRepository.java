package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

interface ParkingRepository extends JpaRepository<Parking, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    Optional<Parking> findByUser(User user);
    List<Parking> findAll();
}
