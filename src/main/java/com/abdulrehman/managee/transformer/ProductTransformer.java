package com.abdulrehman.managee.transformer;

import java.math.BigDecimal;
import java.math.MathContext;

import com.abdulrehman.managee.model.Product;
import com.abdulrehman.managee.model.ProductDiscount;
import com.abdulrehman.managee.model.ProductPriceHistory;
import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.ProductResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
public class ProductTransformer {
	public static ProductResponse convertEntityToResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setId(product.getId());
		productResponse.setActive(product.isActive());
		productResponse.setAvailable(product.isAvailable());
		productResponse.setDescription(product.getDescription());
		productResponse.setDisplayOrder(product.getDisplayOrder());
		productResponse.setGlobal(product.isGlobal());
		productResponse.setName(product.getName());
		productResponse.setUnit(product.getUnit());

		ProductPriceHistory productPriceHistory = product.getProductPriceHistory();
		if (productPriceHistory != null) {
			productResponse.setProductPriceHistoryResponse(
					ProductPriceHistoryTransformer.convertEntityToResponse(productPriceHistory));
			productResponse.setMrp(productPriceHistory.getAmount());
			productResponse.setAmount(productPriceHistory.getAmount());
			productResponse.setQuantity(productPriceHistory.getQuantity());
		}

		ProductDiscount productDiscount = product.getProductDiscount();
		if (productDiscount != null) {
			productResponse
					.setProductDiscountResponse(ProductDiscountTransformer.convertEntityToResponse(productDiscount));
			BigDecimal result = productPriceHistory.getAmount().subtract(productPriceHistory.getAmount()
					.multiply(new BigDecimal((double) productDiscount.getDiscount() / 100)));
			productResponse.setAmount(result.round(MathContext.DECIMAL32));
		}

		return productResponse;
	}

	public static Product convertRequestToEntity(Product product, ProductRequest productRequest) {

		product.setActive(productRequest.isActive());
		product.setAvailable(productRequest.isAvailable());
		product.setDescription(productRequest.getDescription());
		product.setDisplayOrder(productRequest.getDisplayOrder());
		product.setGlobal(productRequest.isGlobal());
		product.setName(productRequest.getName());
		product.setUnit(productRequest.getUnit());

		return product;
	}

	public static Product convertRequestToEntityUpdate(Product product, ProductRequest productRequest) {

		product.setActive(productRequest.isActive());
		product.setAvailable(productRequest.isAvailable());
		product.setDescription(productRequest.getDescription());
		product.setDisplayOrder(productRequest.getDisplayOrder());
		product.setName(productRequest.getName());
		product.setUnit(productRequest.getUnit());

		return product;
	}
}
