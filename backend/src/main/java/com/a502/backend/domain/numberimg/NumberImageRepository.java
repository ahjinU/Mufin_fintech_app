package com.a502.backend.domain.numberimg;

import com.a502.backend.application.entity.NumberImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface NumberImageRepository extends JpaRepository<NumberImage, Integer> {
    List<NumberImage> findAll();
}
