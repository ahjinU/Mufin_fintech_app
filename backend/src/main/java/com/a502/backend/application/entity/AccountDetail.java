package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account_details")
public class AccountDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_detail_id")
	private int id;

	@Column(name = "account_detail_uuid")
	private UUID accountDetailUuid;
	@PrePersist
	public void initUUID() {
		if (accountDetailUuid == null)
			accountDetailUuid = UUID.randomUUID();
	}


	@Column(name = "type")
	private String type;

	@Column(name = "amount")
	private int amount;

	@Column(name = "balance")
	private int balance;

	@Column(name = "counterparty_name")
	private String counterpartyName;

	@Column(name = "state")
	private String state;

	@Column(name = "code_id")
	private int code;

	@Column(name = "counterparty_account")
	private String counterpartyAccount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "receipt_id")
	private Receipt receipt;

	@ManyToOne
	@JoinColumn(name = "memo_id")
	private Memo memo;

	@Builder
	public AccountDetail(String type, int amount, int balance, String counterpartyName, String state, String counterpartyAccount, Account account, int code) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.counterpartyName = counterpartyName;
		this.state = state;
		this.counterpartyAccount = counterpartyAccount;
		this.account = account;
		this.code = code;
	}
}
