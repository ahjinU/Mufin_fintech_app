package com.a502.backend.domain.parking;

import com.a502.backend.application.entity.Parking;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ParkingRepository extends JpaRepository<Parking, Integer> {
    Optional<Parking> findByUser(User user);
}
