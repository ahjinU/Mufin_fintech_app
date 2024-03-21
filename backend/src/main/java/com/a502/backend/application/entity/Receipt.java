package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReceiptDetail> receiptDetails;

	@Builder
	public Receipt(User user) {
		this.user = user;
	}
}
