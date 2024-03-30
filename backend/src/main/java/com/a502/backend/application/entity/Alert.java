package com.a502.backend.application.entity;

import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alerts")
public class Alert extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_id")
	private int id;

	@Column(name = "alert_uuid")
	private UUID alertUuid;
	@PrePersist
	public void initUUID() {
		if (alertUuid == null)
			alertUuid = UUID.randomUUID();
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	//  AL001 적금, AL002 적금만기, AL003 대출요청
	@ManyToOne
	@JoinColumn(name = "code_id")
	private Code code;

	@Builder
	public Alert(User user,Code code) {
		this.user = user;
		this.code = code;
	}
}
