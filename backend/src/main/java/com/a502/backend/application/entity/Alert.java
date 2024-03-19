package com.a502.backend.application.entity;

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
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_id")
	private int id;

	@Column(name = "alert_uuid")
	private UUID alertUuid;

	@Column(name = "type")
	private String type;

	@Column(name = "type_id")
	private int type_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public Alert(String type, int type_id, User user) {
		this.type = type;
		this.type_id = type_id;
		this.user = user;
	}
}
