package com.abdulrehman.managee.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.abdulrehman.managee.model.audit.UserDateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Entity
@Setter
@Getter
public class ProductDiscount extends UserDateAudit {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1952398747687129912L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private String message;

	// Percentage
	private double discount;

	private Instant startDate;
	private Instant endDate;

	public ProductDiscount(String message, double discount, Instant startDate) {
		super();
		this.message = message;
		this.discount = discount;
		this.startDate = startDate;
	}

	public ProductDiscount() {
		super();
	}

}
