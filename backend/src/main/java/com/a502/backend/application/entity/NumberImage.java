package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "number_images")
public class NumberImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int id;

	@Column(name = "image_url")
	private String imageUrl;

	@Builder
	public NumberImage(String imageUrl){
		this.imageUrl = imageUrl;
	}
}
