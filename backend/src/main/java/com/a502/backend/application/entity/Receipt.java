package com.a502.backend.application.entity;

import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipts")
public class Receipt extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private int id;

	@Column(name = "receipt_uuid")
	private UUID receiptUuid;
	@PrePersist
	public void initUUID() {
		if (receiptUuid == null)
			receiptUuid = UUID.randomUUID();
	}

	@Column(name = "price")
	private int price;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_adress")
	private String storeAddress;

	@Column(name = "store_adress")
	private Data paymentDate;


	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReceiptDetail> receiptDetails;

	@Builder
	public Receipt(int id, UUID receiptUuid, int price, String storeName, String storeAddress, List<ReceiptDetail> receiptDetails) {
		this.id = id;
		this.receiptUuid = receiptUuid;
		this.price = price;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.receiptDetails = receiptDetails;
	}
}
