package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memos")
public class Memo extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private int id;

	@Column(name = "memo_uuid")
	private UUID memoUuid;
	@PrePersist
	public void initUUID() {
		if (memoUuid == null)
			memoUuid = UUID.randomUUID();
	}

	@Column(name = "content")
	private String content;

	@Column(name = "category")
	private String category;

	@Builder
	public Memo(String content, String category) {
		this.content = content;
		this.category = category;
	}
}
