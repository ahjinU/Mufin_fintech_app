package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
public class User extends BaseEntity {
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

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "birth")
	private LocalDateTime birth;

	@Column(name = "failed")
	private int failed;

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

	// U001부모, U002아이
	@ManyToOne
	@JoinColumn(name = "code_id")
	private Code code;

	@Builder
	public User(String name, String email, String password, String gender, String address, String address2, String telephone, LocalDateTime birth, User parent, Code code) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.address2 = address2;
		this.telephone = telephone;
		this.birth = birth;
		this.parent = parent;
		this.code = code;
	}
}