package com.a502.backend.application.entity;

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
public class Receipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private int id;

	@Column(name = "receipt_uuid")
	private UUID receiptUuid;

	@OneToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReceiptDetail> receiptDetails;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public Receipt(Memo memo, User user) {
		this.memo = memo;
		this.user = user;
	}
}
