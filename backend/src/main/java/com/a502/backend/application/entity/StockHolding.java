package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock_holdings")
public class StockHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="stock_holding_uuid")
    private byte[] stockHoldingUuid;

    @Column()
    private int cnt;

    @Column()
    private int total;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @Builder
    public StockHolding(int id, byte[] stockHoldingUuid, int cnt, int total, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted){
        this.id = id;
        this.stockHoldingUuid = stockHoldingUuid;
        this.cnt = cnt;
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }
}
