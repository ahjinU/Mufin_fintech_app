package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account_details")
public class AccountDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_detail_id")
	private Integer accountDetailId;

	@Column(name = "account_detail_uuid")
	private byte[] accountDetailUuid;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "type")
	private String type;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "balance")
	private Integer balance;

	@Column(name = "counterparty_name")
	private String counterpartyName;

	@Column(name = "state")
	private String state;

	@Column(name = "counterparty_account")
	private String counterpartyAccount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_at")
	private Date modifiedAt;

	@ManyToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "code_id")
	private Integer code;

}
