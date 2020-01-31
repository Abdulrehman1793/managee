package com.abdulrehman.managee.transformer;

import com.abdulrehman.managee.model.Product;
import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.ProductResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
public class ProductTransformer {
	public static ProductResponse convertEntityToResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setActive(product.isActive());
		productResponse.setAvailable(product.isAvailable());
		productResponse.setCategory(product.isCategory());
		productResponse.setDescription(product.getDescription());
		productResponse.setDisplayOrder(product.getDisplayOrder());
		productResponse.setGlobal(product.isGlobal());
		productResponse.setName(product.getName());
		productResponse.setUnit(product.getUnit());
		productResponse.setId(product.getId());

		if (product.getProductPriceHistory() != null)
			productResponse.setProductPriceHistoryResponse(
					ProductPriceHistoryTransformer.convertEntityToResponse(product.getProductPriceHistory()));

		return productResponse;
	}

	public static Product convertRequestToEntity(Product product, ProductRequest productRequest) {

		product.setActive(productRequest.isActive());
		product.setAvailable(productRequest.isAvailable());
		product.setCategory(productRequest.isCategory());
		product.setDescription(productRequest.getDescription());
		product.setDisplayOrder(productRequest.getDisplayOrder());
		product.setGlobal(productRequest.isGlobal());
		product.setName(productRequest.getName());
		product.setUnit(productRequest.getUnit());

		return product;
	}
}
