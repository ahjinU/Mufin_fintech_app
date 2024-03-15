package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

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
	private byte[] alertUuid;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "content")
	private String content;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Alert(int id, byte[] alertUuid, LocalDateTime createdAt, boolean isDeleted, String content, String type, User user) {
		this.id = id;
		this.alertUuid = alertUuid;
		this.createdAt = createdAt;
		this.isDeleted = isDeleted;
		this.content = content;
		this.type = type;
		this.user = user;
	}
}
