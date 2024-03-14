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
@Table(name = "dutch_details")
public class DutchDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dutch_detail_id")
	private int id;

	@Column(name = "dutch_detail_uuid")
	private byte[] dutchDetailUuid;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@OneToOne
	@JoinColumn(name = "account_detail_id")
	private AccountDetail accountDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dutch_id", referencedColumnName = "dutch_id")
	private Dutch dutch;

	@Builder
	public DutchDetail(int id, byte[] dutchDetailUuid, AccountDetail accountDetail, Dutch dutch, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.id = id;
		this.dutchDetailUuid = dutchDetailUuid;
		this.accountDetail = accountDetail;
		this.dutch = dutch;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
}
