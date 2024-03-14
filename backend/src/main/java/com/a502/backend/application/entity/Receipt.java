package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipts")
public class Receipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private int id;

	@Column(name = "receipt_uuid")
	private byte[] receiptUuid;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@OneToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReceiptDetail> receiptDetails;

	@Builder
	public Receipt(int id, byte[] receiptUuid, LocalDateTime createdAt, Memo memo, User user, boolean isDeleted, List<ReceiptDetail> receiptDetails) {
		this.id = id;
		this.receiptUuid = receiptUuid;
		this.createdAt = createdAt;
		this.memo = memo;
		this.user = user;
		this.isDeleted = isDeleted;
		this.receiptDetails = receiptDetails;
	}
}
