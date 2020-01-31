package com.abdulrehman.managee.transformer;

import com.abdulrehman.managee.model.ProductDiscount;
import com.abdulrehman.managee.payload.request.ProductDiscountRequest;
import com.abdulrehman.managee.payload.response.ProductDiscountResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
public class ProductDiscountTransformer {

	public static ProductDiscountResponse convertEntityToResponse(ProductDiscount productDiscount) {
		ProductDiscountResponse productDiscountResponse = new ProductDiscountResponse();

		productDiscountResponse.setId(productDiscount.getId());
		productDiscountResponse.setEndDate(productDiscount.getEndDate());
		productDiscountResponse.setDiscount(productDiscount.getDiscount());
		productDiscountResponse.setMessage(productDiscount.getMessage());
		productDiscountResponse.setStartDate(productDiscount.getStartDate());

		return productDiscountResponse;
	}

	public static ProductDiscount convertRequestToEntity(ProductDiscount productDiscount,
			ProductDiscountRequest productDiscountRequest) {

		productDiscount.setDiscount(productDiscountRequest.getDiscount());
		productDiscount.setEndDate(productDiscountRequest.getEndDate());
		productDiscount.setMessage(productDiscountRequest.getMessage());
		productDiscount.setStartDate(productDiscountRequest.getStartDate());

		return productDiscount;
	}
}
