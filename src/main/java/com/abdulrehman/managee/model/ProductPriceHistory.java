package com.abdulrehman.managee.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.abdulrehman.managee.model.audit.UserDateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 28, 2020
 */
@Entity
@Setter
@Getter
public class ProductPriceHistory extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1944379655269132364L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private String message;

	private BigInteger quantity;
	private BigDecimal amount;

	private Instant startDate;
	private Instant endDate;

	public ProductPriceHistory() {
		super();
	}

	public ProductPriceHistory(BigInteger quantity, BigDecimal amount, Instant startDate) {
		super();
		this.quantity = quantity;
		this.amount = amount;
		this.startDate = startDate;

	}

	public ProductPriceHistory(BigInteger quantity, BigDecimal amount, Instant startDate, Instant endDate) {
		super();
		this.quantity = quantity;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ProductPriceHistory(String message, BigInteger quantity, BigDecimal amount, Instant startDate) {
		super();
		this.message = message;
		this.quantity = quantity;
		this.amount = amount;
		this.startDate = startDate;
	}
}
