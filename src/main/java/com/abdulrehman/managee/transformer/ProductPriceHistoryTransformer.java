package com.abdulrehman.managee.transformer;

import com.abdulrehman.managee.model.ProductPriceHistory;
import com.abdulrehman.managee.payload.request.ProductPriceHistoryRequest;
import com.abdulrehman.managee.payload.response.ProductPriceHistoryResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
public class ProductPriceHistoryTransformer {
	public static ProductPriceHistoryResponse convertEntityToResponse(ProductPriceHistory productPriceHistory) {
		ProductPriceHistoryResponse productPriceHistoryResponse = new ProductPriceHistoryResponse();

		productPriceHistoryResponse.setAmount(productPriceHistory.getAmount());
		productPriceHistoryResponse.setEndDate(productPriceHistory.getEndDate());
		productPriceHistoryResponse.setId(productPriceHistory.getId());
		productPriceHistoryResponse.setMessage(productPriceHistory.getMessage());
		productPriceHistoryResponse.setQuantity(productPriceHistory.getQuantity());
		productPriceHistoryResponse.setStartDate(productPriceHistory.getStartDate());

		return productPriceHistoryResponse;
	}

	public static ProductPriceHistory convertRequestToEntity(ProductPriceHistory productPriceHistory,
			ProductPriceHistoryRequest productPriceHistoryRequest) {

		productPriceHistory.setAmount(productPriceHistoryRequest.getAmount());
		productPriceHistory.setEndDate(productPriceHistoryRequest.getEndDate());
		productPriceHistory.setMessage(productPriceHistoryRequest.getMessage());
		productPriceHistory.setQuantity(productPriceHistoryRequest.getQuantity());
		productPriceHistory.setStartDate(productPriceHistoryRequest.getStartDate());

		return productPriceHistory;
	}
}
