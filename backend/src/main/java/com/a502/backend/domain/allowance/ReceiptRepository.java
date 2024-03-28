package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    @Query("SELECT r FROM Receipt r JOIN FETCH r.receiptDetails WHERE r.id = :id")
    Optional<Receipt> findByIdWithDetails(@Param("id") Long id);
}
