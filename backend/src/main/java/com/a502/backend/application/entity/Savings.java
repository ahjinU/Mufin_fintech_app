package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "savings")
public class Savings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saving_id")
	private int id;

	@Column(name = "saving_uuid")
	private byte[] savingUuid;

	@Column(name = "interest")
	private double interest;

	@Column(name = "period")
	private int period;

	@Column(name = "name")
	private String name;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Savings(int id, byte[] savingUuid, double interest, int period, String name, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, User user) {
		this.id = id;
		this.savingUuid = savingUuid;
		this.interest = interest;
		this.period = period;
		this.name = name;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.user = user;
	}
}
