package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="stock_id")
    private int id;

    @Column(name="stock_uuid")
    private byte[] stockUuid;

    @Column()
    private String name;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "stock")
    private List<StockSell> stockSells = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<StockBuy> stockBuys = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<StockDetail> stockDetails = new ArrayList<>();
}
