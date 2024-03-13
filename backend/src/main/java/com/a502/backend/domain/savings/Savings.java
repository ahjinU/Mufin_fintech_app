package com.a502.backend.domain.savings;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savings")
    private Integer userId;
}
