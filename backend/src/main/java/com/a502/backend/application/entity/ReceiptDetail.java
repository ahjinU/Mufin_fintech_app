package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipt_details")
public class ReceiptDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_detail_id")
	private int id;

	@Column(name = "receipt_detail_uuid")
	private UUID receiptDetailUuid;
	@PrePersist
	public void initUUID() {
		if (receiptDetailUuid == null)
			receiptDetailUuid = UUID.randomUUID();
	}

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

	@Builder
	public ReceiptDetail(String item, int price, int cnt, int total, Receipt receipt) {
		this.item = item;
		this.price = price;
		this.cnt = cnt;
		this.total = total;
		this.receipt = receipt;
	}
}
