package com.abdulrehman.managee.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.abdulrehman.managee.payload.request.CategoryRequest;
import com.abdulrehman.managee.payload.response.CategoryResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
public interface CategoryService {

	public List<CategoryResponse> findPage(Integer pageNo, Integer pageSize, Sort sort);

	public CategoryResponse createCategory(CategoryRequest categoryRequest);

	public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

	public DeleteResponse deleteCategory(Long categoryId);
}
