package com.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Getter;

@Getter
@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;

	private Long quantity;

	@Version
	private Long version;

	public Stock() {
	}

	public Stock(Long id, Long quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public void decrease(Long quantity) {
		if (this.quantity - quantity < 0) {
			throw new RuntimeException("foo");
		}

		this.quantity -= quantity;
	}
}
