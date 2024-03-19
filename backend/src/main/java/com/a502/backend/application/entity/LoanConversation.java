package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
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
public class LoanConversation extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_conversation_id")
	private int id;

	@Column(name = "loan_conversation_uuid")
	private UUID loanConversationUuid;
	@PrePersist
	public void initUUID() {
		if (loanConversationUuid == null)
			loanConversationUuid = UUID.randomUUID();
	}

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public LoanConversation(String content, User user) {
		this.content = content;
		this.user = user;
	}
}
