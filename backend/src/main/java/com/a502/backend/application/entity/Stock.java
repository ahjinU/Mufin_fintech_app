package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stocks")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private int id;

	@Column(name = "stock_uuid")
	private byte[] stockUuid;

	@Column(name = "name")
	private String name;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany(mappedBy = "stock")
	private List<StockSell> stockSells = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockBuy> stockBuys = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockDetail> stockDetails = new ArrayList<>();

	@OneToMany(mappedBy = "stock")
	private List<StockHolding> stockHoldings = new ArrayList<>();

	@Builder
	public Stock(int id, byte[] stockUuid, String name, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, List<StockSell> stockSells, List<StockBuy> stockBuys, List<StockHolding> stockHoldings, List<StockDetail> stockDetails) {
		this.id = id;
		this.stockUuid = stockUuid;
		this.name = name;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.isDeleted = isDeleted;
		this.stockSells = stockSells;
		this.stockBuys = stockBuys;
		this.stockHoldings = stockHoldings;
		this.stockDetails = stockDetails;
	}
}
