package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipt_details")
public class ReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_detail_id")
    private Integer receiptDetailId;

    @Column(name = "receipt_detail_uuid")
    private byte[] receiptDetailUuid;

    @Column(name = "item")
    private String item;

    @Column(name = "price")
    private Integer price;

    @Column(name = "cnt")
    private Integer cnt;

    @Column(name = "total")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder

    public ReceiptDetail(Integer receiptDetailId, byte[] receiptDetailUuid, String item, Integer price, Integer cnt, Integer total, Receipt receipt, Boolean isDeleted) {
        this.receiptDetailId = receiptDetailId;
        this.receiptDetailUuid = receiptDetailUuid;
        this.item = item;
        this.price = price;
        this.cnt = cnt;
        this.total = total;
        this.receipt = receipt;
        this.isDeleted = isDeleted;
    }
}
