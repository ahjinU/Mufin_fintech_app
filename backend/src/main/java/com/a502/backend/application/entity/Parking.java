package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parkings")
public class Parking extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parking_id")
	private int id;

	@Column(name = "parking_uuid")
	private UUID parkingUuid;
	@PrePersist
	public void initUUID() {
		if (parkingUuid == null)
			parkingUuid = UUID.randomUUID();
	}

	@Setter
	@Column(name = "balance")
	private int balance;

	@Column(name = "interest")
	private double interest;

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
