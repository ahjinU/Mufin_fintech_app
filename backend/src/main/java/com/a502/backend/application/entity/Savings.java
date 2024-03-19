package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "savings")
public class Savings extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saving_id")
	private int id;

	@Column(name = "saving_uuid")
	private UUID savingUuid;
	@PrePersist
	public void initUUID() {
		if (savingUuid == null)
			savingUuid = UUID.randomUUID();
	}

	@Column(name = "interest")
	private double interest;

	@Column(name = "period")
	private int period;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User parent;

	@Builder
	public Savings(double interest, int period, String name, User parent) {
		this.interest = interest;
		this.period = period;
		this.name = name;
		this.parent = parent;
	}
}
