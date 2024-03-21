package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stocks")
public class Stock extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private int id;

	@Column(name = "stock_uuid")
	private UUID stockUuid;
	@PrePersist
	public void initUUID() {
		if (stockUuid == null)
			stockUuid = UUID.randomUUID();
	}

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "stock")
	private List<StockSell> stockSells = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockBuy> stockBuys = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockDetail> stockDetails = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockHolding> stockHoldings = new ArrayList<>();

	@Builder
	public Stock(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
}
