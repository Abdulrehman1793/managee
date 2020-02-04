package com.abdulrehman.managee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdulrehman.managee.payload.request.ProductRequest;
import com.abdulrehman.managee.payload.response.ProductResponse;
import com.abdulrehman.managee.service.ProductService;
import com.abdulrehman.managee.util.AppUrls;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 31, 2020
 */
@RestController
@RequestMapping(AppUrls.PRODUCT)
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}

	@PostMapping("/update/{id}")
	public ProductResponse updateProduct(@PathVariable(name = "id") Long productId,
			@Valid @RequestBody ProductRequest productRequest) {
		return productService.updateProduct(productId, productRequest);
	}
}
