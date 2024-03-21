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

	@Column(name = "amount")
	private int amount;

	@Column(name = "balance")
	private int balance;

	@Column(name = "counterparty_name")
	private String counterpartyName;

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

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// ADT001대출, ADT002적금, ADT003결제, ADT004계좌이체, ADT005용돈
	@ManyToOne
	@JoinColumn(name = "account_datail_type_id")
	private Code accountDetailTypeCode;

	// ADS001거래 완료 / ADS002거래 취소
	@ManyToOne
	@JoinColumn(name = "account_detail_status_id")
	private Code accountDetailStatusCode;

	@Builder
	public AccountDetail(int amount, int balance, String counterpartyName, String counterpartyAccount, Account account, Category category, Code accountDetailTypeCode, Code accountDetailStatusCode) {
		this.amount = amount;
		this.balance = balance;
		this.counterpartyName = counterpartyName;
		this.counterpartyAccount = counterpartyAccount;
		this.account = account;
		this.category = category;
		this.accountDetailTypeCode = accountDetailTypeCode;
		this.accountDetailStatusCode = accountDetailStatusCode;
	}
}
