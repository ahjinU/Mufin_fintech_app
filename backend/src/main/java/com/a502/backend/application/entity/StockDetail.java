package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_details")
public class StockDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_detail_id")
    private int id;

    @Column(name="stock_detail_uuid")
    private byte[] stockDetailUuid;

    @Column()
    private int price;

    @Column(name="highest_price")
    private int highestPrice;

    @Column(name="lowest_price")
    private int lowestPrice;

    @Column(name="upper_limit_price")
    private int upperLimitPrice;

    @Column(name="lower_limit_price")
    private int lowerLimitPrice;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Builder
    public StockDetail(int id, byte[] stockDetailUuid, int price, int highestPrice, int lowestPrice, int upperLimitPrice, int lowerLimitPrice, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Stock stock){
        this.id = id;
        this.stockDetailUuid = stockDetailUuid;
        this.price = price;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.upperLimitPrice = upperLimitPrice;
        this.lowerLimitPrice = lowerLimitPrice;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.stock = stock;
    }
}
