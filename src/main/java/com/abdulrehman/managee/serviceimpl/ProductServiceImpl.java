package com.abdulrehman.managee.serviceimpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Product;
import com.abdulrehman.managee.model.ProductDiscount;
import com.abdulrehman.managee.model.ProductPriceHistory;
import com.abdulrehman.managee.payload.request.ProductDiscountRequest;
import com.abdulrehman.managee.payload.request.ProductPriceHistoryRequest;
import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.payload.response.ProductResponse;
import com.abdulrehman.managee.repository.ProductRepository;
import com.abdulrehman.managee.service.ProductService;
import com.abdulrehman.managee.transformer.ProductPriceHistoryTransformer;
import com.abdulrehman.managee.transformer.ProductTransformer;
import com.abdulrehman.managee.util.AppConstants;

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
		ProductPriceHistoryRequest productPriceHistoryRequest = productRequest.getProductPriceHistoryRequest();
		if (productPriceHistoryRequest != null)
			product.addproductPriceHistory(ProductPriceHistoryTransformer
					.convertRequestToEntity(new ProductPriceHistory(), productPriceHistoryRequest));
		else {
			// Add a product price history default value
			product.addproductPriceHistory(new ProductPriceHistory(AppConstants.PPH_MESSAGE, AppConstants.PPH_QUANTITY,
					AppConstants.PPH_AMOUNT, Instant.now()));
		}

		ProductDiscountRequest productDiscountRequest = productRequest.getProductDiscountRequest();
		// Adding product discount.
		if (productDiscountRequest != null)
			newProductDiscount(product, productDiscountRequest);

		product = productRepository.save(product);

		return ProductTransformer.convertEntityToResponse(product);
	}

	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new BadRequestException("Invalid product."));

		product = ProductTransformer.convertRequestToEntityUpdate(product, productRequest);

		// UPDATE: product price history
		updateProductPriceHistory(product, productRequest.getProductPriceHistoryRequest());

		// UPDATE: product discount
		updateProductDiscount(product, productRequest.getProductDiscountRequest());

		product = productRepository.save(product);

		return ProductTransformer.convertEntityToResponse(product);
	}

	@Override
	public DeleteResponse deleteProduct(Long productId) {

		productRepository.findById(productId).orElseThrow(() -> new BadRequestException("Invalid product."));

		return new DeleteResponse("Product deleted successfully.");
	}

	private void newProductDiscount(Product product, ProductDiscountRequest productDiscountRequest) {
		if (productDiscountRequest.getDiscount() > 100)
			throw new BadRequestException("Discount percentage should be less than 100.");

		// Create New product discount
		product.addProductDiscount(new ProductDiscount(productDiscountRequest.getMessage(),
				productDiscountRequest.getDiscount(), Instant.now()));
	}

	private void updateProductPriceHistory(Product product, ProductPriceHistoryRequest productPriceHistoryRequest) {
		if (productPriceHistoryRequest != null) {
			// Get last PPH
			ProductPriceHistory productPriceHistory = product.getProductPriceHistory();

			/**
			 * Update product price history - PPH. If product price amount/quantity changed
			 * then update last PPH end date and create new entry of PPH with null end date.
			 */
			if (productPriceHistory.getAmount().compareTo(productPriceHistoryRequest.getAmount()) != 0
					|| productPriceHistory.getQuantity().compareTo(productPriceHistoryRequest.getQuantity()) != 0) {
				// Update last PPH entry
				productPriceHistory.setEndDate(Instant.now());
				// Create New PPH
				product.addproductPriceHistory(new ProductPriceHistory(productPriceHistoryRequest.getMessage(),
						productPriceHistoryRequest.getQuantity(), productPriceHistoryRequest.getAmount(),
						Instant.now()));
			} else {
				productPriceHistory.setMessage(productPriceHistoryRequest.getMessage());
			}
		}
	}

	private void updateProductDiscount(Product product, ProductDiscountRequest productDiscountRequest) {
		// UPDATE: product discount
		if (productDiscountRequest != null) {
			ProductDiscount productDiscount = product.getProductDiscount();

			// Product discount could be null.
			if (productDiscount != null) {
				if (productDiscount.getDiscount() != productDiscountRequest.getDiscount()) {
					// Update last product discount entry
					productDiscount.setEndDate(Instant.now());
					newProductDiscount(product, productDiscountRequest);
				} else {
					productDiscount.setMessage(productDiscountRequest.getMessage());
				}
			} else
				newProductDiscount(product, productDiscountRequest);
		}
	}
}
