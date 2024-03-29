package com.a502.backend.application.entity;

import com.a502.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
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

	@Builder
	public Memo(String content) {
		this.content = content;
	}
}
