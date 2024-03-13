package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Integer receiptId;

    @Column(name = "receipt_uuid")
    private byte[] receiptUuid;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptDetail> receiptDetails;

    @Builder
    public Receipt(Integer receiptId, byte[] receiptUuid, Date createdAt, Memo memo, User user, Boolean isDeleted, List<ReceiptDetail> receiptDetails) {
        this.receiptId = receiptId;
        this.receiptUuid = receiptUuid;
        this.createdAt = createdAt;
        this.memo = memo;
        this.user = user;
        this.isDeleted = isDeleted;
        this.receiptDetails = receiptDetails;
    }
}
