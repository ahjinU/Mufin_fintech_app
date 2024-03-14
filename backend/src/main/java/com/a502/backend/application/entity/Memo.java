package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memos")
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private int id;

	@Column(name = "memo_uuid")
	private byte[] memoUuid;

	@Column(name = "content")
	private String content;

	@Column(name = "category")
	private String category;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Builder
	public Memo(int id, byte[] memoUuid, String content, String category, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted) {
		this.id = id;
		this.memoUuid = memoUuid;
		this.content = content;
		this.category = category;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
	}
}
