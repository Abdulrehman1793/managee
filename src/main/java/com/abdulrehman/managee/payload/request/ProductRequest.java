package com.abdulrehman.managee.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.abdulrehman.managee.model.ProductUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Getter
@Setter
public class ProductRequest {
	@NotBlank(message = "{name.notblank}")
	private String name;
	private String description;
	private boolean isGlobal;
	private ProductUnit unit;

	@Min(value = 1, message = "{displayOrder.min}")
	private int displayOrder;
	private boolean active;
	private boolean isAvailable;

	private ProductPriceHistoryRequest productPriceHistoryRequest;
	private ProductDiscountRequest productDiscountRequest;
}
