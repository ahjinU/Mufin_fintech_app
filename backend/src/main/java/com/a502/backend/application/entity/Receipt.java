package com.a502.backend.application.entity;

import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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


	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReceiptDetail> receiptDetails = new ArrayList<>();

	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		receiptDetails.add(receiptDetail);
		receiptDetail.addReceipt(this);
	}

	@Builder
	public Receipt(int price, String storeName, String storeAddress) {
		this.price = price;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
	}
	public static Receipt createReceipt(ReceiptDto receiptDto) {
		return Receipt.builder()
				.storeName(receiptDto.getStoreInfo().getName())
				.storeAddress(receiptDto.getStoreInfo().getAddress())
				.price(receiptDto.getPaymentInfo().getPrice())
				.build();
	}

}
