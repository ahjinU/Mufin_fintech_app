package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
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

	@Column(name = "type")
	private String type;

	@Column(name = "type_id")
	private int type_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Alert(String type, int type_id, User user) {
		this.type = type;
		this.type_id = type_id;
		this.user = user;
	}
}
