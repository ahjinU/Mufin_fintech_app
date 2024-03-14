package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipt_details")
public class ReceiptDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_detail_id")
	private int id;

	@Column(name = "receipt_detail_uuid")
	private byte[] receiptDetailUuid;

	@Column(name = "item")
	private String item;

	@Column(name = "price")
	private int price;

	@Column(name = "cnt")
	private int cnt;

	@Column(name = "total")
	private int total;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Builder

	public ReceiptDetail(int id, byte[] receiptDetailUuid, String item, int price, int cnt, int total, Receipt receipt, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.id = id;
		this.receiptDetailUuid = receiptDetailUuid;
		this.item = item;
		this.price = price;
		this.cnt = cnt;
		this.total = total;
		this.receipt = receipt;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
}
