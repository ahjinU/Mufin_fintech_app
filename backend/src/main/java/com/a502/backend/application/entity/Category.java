package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@Builder
	public Category(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
}
