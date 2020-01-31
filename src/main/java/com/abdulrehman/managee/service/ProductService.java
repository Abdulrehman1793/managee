package com.abdulrehman.managee.service;

import java.util.List;

import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.ProductResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
public interface ProductService {
	public List<ProductResponse> getAllProductList(Integer pageNo, Integer pageSize, String sortBy);

	public ProductResponse createProduct(ProductRequest productRequest);
}
