package com.a502.backend.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "codes")
public class Code {
	@Id
	@Column(name = "code_id")
	private String id;

	@Column(name = "name")
	private String name;

	@Builder
	public Code(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
