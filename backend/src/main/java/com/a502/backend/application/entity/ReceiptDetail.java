package com.a502.backend.application.entity;

import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

	@Column(name = "cnt")
	private int cnt;

	@Column(name = "total")
	private int total;

	@Column(name = "unit_price")
	private int unitPrice;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@Builder
	public ReceiptDetail(String item, int cnt, int total, int unitPrice, Receipt receipt) {
		this.item = item;
		this.cnt = cnt;
		this.total = total;
		this.unitPrice = unitPrice;
		this.receipt = receipt;
	}

	public void addReceipt(Receipt receipt) {
		this.receipt=receipt;
	}


	public List<ReceiptDetail> convertFromDtoList(List<OrderItem> orderItems, Receipt receipt) {

		List<ReceiptDetail> details = new ArrayList<>();

		for(OrderItem orderItem : orderItems){
			convertFromDto(orderItem,receipt);
		}

		return details;
	}

	private ReceiptDetail convertFromDto(OrderItem item,Receipt receipt){

		return ReceiptDetail.builder()
				.item(item.getItem())
				.cnt(item.getCnt())
				.total(item.getTotal())
				.unitPrice(item.getUnitPrice())
				.receipt(receipt)
				.build();
	}
}
