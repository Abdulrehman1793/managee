package com.abdulrehman.managee.transformer;

import com.abdulrehman.managee.model.Category;
import com.abdulrehman.managee.payload.request.CategoryRequest;
import com.abdulrehman.managee.payload.response.CategoryResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
public class CategoryTransformer {
	public static Category convertRequestToEntity(Category category, CategoryRequest categoryRequest) {

		category.setDisplayOrder(categoryRequest.getDisplayOrder());
		category.setActive(categoryRequest.isActive());
		category.setCategories(categoryRequest.getCategories());
		category.setDescription(categoryRequest.getDescription());
		category.setName(categoryRequest.getName());
		category.setProducts(categoryRequest.getProducts());

		return category;
	}

	public static CategoryResponse convertEntityToResponse(Category category) {
		CategoryResponse categoryResponse = new CategoryResponse();

		categoryResponse.setDisplayOrder(category.getDisplayOrder());
		categoryResponse.setId(category.getId());
		categoryResponse.setActive(category.isActive());
		categoryResponse.setCategories(category.getCategories());
		categoryResponse.setDescription(category.getDescription());
		categoryResponse.setName(category.getName());
		categoryResponse.setProducts(category.getProducts());

		return categoryResponse;
	}
}
