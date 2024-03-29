package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
    Optional<Parking> findByUser(User user);
    List<Parking> findAll();
}
