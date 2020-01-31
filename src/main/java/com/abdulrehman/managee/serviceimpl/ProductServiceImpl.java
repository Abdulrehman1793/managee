package com.abdulrehman.managee.serviceimpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.model.Product;
import com.abdulrehman.managee.model.ProductDiscount;
import com.abdulrehman.managee.model.ProductPriceHistory;
import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.ProductResponse;
import com.abdulrehman.managee.repository.ProductRepository;
import com.abdulrehman.managee.service.ProductService;
import com.abdulrehman.managee.transformer.ProductDiscountTransformer;
import com.abdulrehman.managee.transformer.ProductPriceHistoryTransformer;
import com.abdulrehman.managee.transformer.ProductTransformer;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductResponse> getAllProductList(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pagedResult = productRepository.findAll(pageable);
		if (pagedResult.hasContent()) {
			return convertEntityToResponseList(pagedResult.getContent());
		} else {
			return new ArrayList<ProductResponse>();
		}
	}

	private List<ProductResponse> convertEntityToResponseList(List<Product> products) {
		final ArrayList<ProductResponse> productResponses = new ArrayList<ProductResponse>();

		products.forEach(row -> productResponses.add(ProductTransformer.convertEntityToResponse(row)));

		return productResponses;
	}

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {

		Product product = ProductTransformer.convertRequestToEntity(new Product(), productRequest);

		// Adding product price history.
		if (productRequest.getProductPriceHistoryRequest() != null)
			product.addproductPriceHistory(ProductPriceHistoryTransformer
					.convertRequestToEntity(new ProductPriceHistory(), productRequest.getProductPriceHistoryRequest()));
		else {
			// Add a product price history default value
			product.addproductPriceHistory(
					new ProductPriceHistory("Auto Generated", new BigInteger("1"), new BigDecimal("1"), Instant.now()));
		}

		// Adding product discount.
		if (productRequest.getProductDiscountRequest() != null)
			product.addproductDiscount(ProductDiscountTransformer.convertRequestToEntity(new ProductDiscount(),
					productRequest.getProductDiscountRequest()));

		product = productRepository.save(product);

		return ProductTransformer.convertEntityToResponse(product);
	}

}
