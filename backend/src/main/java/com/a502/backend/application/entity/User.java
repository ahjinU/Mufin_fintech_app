package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_uuid")
	private UUID userUuid;

	@PrePersist
	public void initUUID() {
		if (userUuid == null)
			userUuid = UUID.randomUUID();
	}

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "address2")
	private String address2;

	@Column(name = "type")
	private int type;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "birth")
	private LocalDateTime birth;

	@ColumnDefault("0")
	@Column(name = "failed")
	private Short failed;

	@ColumnDefault("false")
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private User parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<User> childrens = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alert> alerts = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<StockHolding> stockHoldings = new ArrayList<>();

	@Builder
	public User(String name, String email, String password, String gender, String address, String address2, int type, String telephone, LocalDateTime birth, User parent) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.address2 = address2;
		this.type = type;
		this.telephone = telephone;
		this.birth = birth;
		this.parent = parent;
	}
}
