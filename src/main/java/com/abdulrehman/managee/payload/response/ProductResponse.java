package com.abdulrehman.managee.payload.response;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abdulrehman.managee.model.ProductUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Getter
@Setter
public class ProductResponse {
	private Long id;

	private String name;
	private String description;
	private ProductUnit unit;

	private boolean isGlobal;

	private int displayOrder;
	private boolean active;
	private boolean isAvailable;

	private BigInteger quantity;
	private BigDecimal mrp;
	private BigDecimal amount;

	private ProductPriceHistoryResponse productPriceHistoryResponse;
	private ProductDiscountResponse productDiscountResponse;
}
