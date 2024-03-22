package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parking_details")
public class ParkingDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parking_details_id")
	private int id;

	@Column(name = "parking_details_uuid")
	private UUID parkingDetailUuid;
	@PrePersist
	public void initUUID() {
		if (parkingDetailUuid == null)
			parkingDetailUuid = UUID.randomUUID();
	}

	@Column(name = "counterparty_name")
	private String counterpartyName;

	@Column(name = "cnt")
	private int cnt;

	@Column(name = "amount")
	private int amount;

	@Column(name = "balance")
	private int balance;

	@Column(name="ratio")
	private double ratio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_id")
	private Parking parking;

	// 이자, 매도, 매수
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_id")
	private Code code;

	@Builder
	public ParkingDetail(String counterpartyName, int cnt, int amount, int balance, double ratio, Parking parking, Code code) {
		this.counterpartyName = counterpartyName;
		this.cnt = cnt;
		this.amount = amount;
		this.balance = balance;
		this.ratio = ratio;
		this.parking = parking;
		this.code = code;
	}
}
