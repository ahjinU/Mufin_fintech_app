package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parkings")
public class Parking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parking_id")
	private int id;

	@Column(name = "parking_uuid")
	private UUID parkingUuid;

	@Column(name = "balance")
	private int balance;

	@Column(name = "interest")
	private double interest;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@ColumnDefault("false")
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Parking(int balance, int interest, User user) {
		this.balance = balance;
		this.interest = interest;
		this.user = user;
	}
}
