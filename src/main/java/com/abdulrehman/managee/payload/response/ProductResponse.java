package com.abdulrehman.managee.payload.response;

import java.util.List;

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
	private boolean isCategory;

	private int displayOrder;
	private boolean active;
	private boolean isAvailable;

	private ProductPriceHistoryResponse productPriceHistoryResponse;
	private ProductDiscountResponse productDiscountResponse;

	private List<ProductResponse> childrens;
}
