package com.a502.backend.application.entity;


import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cash_details")
public class CashDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_detail_id")
    private int id;

    @Column(name = "trans_at")
    private LocalDateTime transAt;

    @Column(name = "usage_name")
    private String usageName;

    @Column(name = "cash_detail_uuid")
    private UUID cashDetailUuid; // Assuming binary UUID storage, adjust if using a different type

    @PrePersist
    public void initUUID() {
        if (cashDetailUuid == null)
            cashDetailUuid = UUID.randomUUID();
    }

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Builder
    public CashDetail(int amount, User user, Category category, String usageName, LocalDateTime transAt) {
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.transAt=transAt;
        this.usageName = usageName;
    }

    public void updateMemo(Memo memo) {
        this.memo = memo;
    }

    public void updateReceipt(Receipt receipt){
        this.receipt=receipt;
    }
}
