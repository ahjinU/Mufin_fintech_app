package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private UUID loanConversationUuid;

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Builder
	public LoanConversation(String content, User user) {
		this.content = content;
		this.user = user;
	}
}
