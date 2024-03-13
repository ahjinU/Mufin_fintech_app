package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "loan_conversations")
public class LoanConversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_conversation_id")
	private int id;

	@Column(name = "loan_conversation_uuid")
	private byte[] loanConversationUuid;

	@Column()
	private String content;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public LoanConversation(int id, byte[] loanConversationUuid, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted) {
		this.id = id;
		this.loanConversationUuid = loanConversationUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
	}
}
