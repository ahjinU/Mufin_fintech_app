package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parking_details")
public class ParkingDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parking_details_id")
	private int id;

	@Column(name = "parking_details_uuid")
	private UUID parkingDetailUuid;

	@Column(name = "trans_code")
	private int transCode;

	@Column(name = "amount")
	private int amount;

	@Column(name = "balance")
	private int balance;

	@Column(name = "memo")
	private String memo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_id")
	private Parking parking;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Builder
	public ParkingDetail(int transCode, int amount, int balance, String memo, Parking parking) {
		this.transCode = transCode;
		this.amount = amount;
		this.balance = balance;
		this.memo = memo;
		this.parking = parking;
	}
}
