package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
	private byte[] parkingDetailUuid;

	@Column(name = "trans_code")
	private int transCode;

	@Column()
	private int amount;

	@Column()
	private int balance;

	@Column()
	private String memo;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne()
	@JoinColumn(name = "parking_id")
	private Parking parking;

	@Builder
	public ParkingDetail(int id, byte[] parkingDetailUuid, int transCode, int amount, int balance, String memo, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, Parking parking) {
		this.id = id;
		this.parkingDetailUuid = parkingDetailUuid;
		this.transCode = transCode;
		this.amount = amount;
		this.balance = balance;
		this.memo = memo;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.parking = parking;
	}
}
