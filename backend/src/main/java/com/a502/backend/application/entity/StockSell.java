package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stock_sells")
public class StockSell{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sotck_sell_id")
    private int id;

    @Column(name="stock_sell_uuid")
    private byte[] stockSellUuid;

    @Column()
    private int price;

    @Column(name="cnt_total")
    private int cntTotal;

    @Column(name="cnt_not")
    private int cntNot;

    @Column()
    private int status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="stock_id")
    private Stock stock;
}
