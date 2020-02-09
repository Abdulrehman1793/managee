package com.abdulrehman.managee.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Category;
import com.abdulrehman.managee.payload.request.CategoryRequest;
import com.abdulrehman.managee.payload.response.CategoryResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.repository.CategoryRepository;
import com.abdulrehman.managee.security.UserPrincipal;
import com.abdulrehman.managee.service.CategoryService;
import com.abdulrehman.managee.transformer.CategoryTransformer;
import com.abdulrehman.managee.util.AppConstants;
import com.abdulrehman.managee.util.AppUtils;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryResponse> findPage(Integer pageNo, Integer pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Category> pagedResult = categoryRepository.findAll(pageable);

		if (pagedResult.hasContent()) {
			return convertEntityToResponseList(pagedResult.getContent());
		} else {
			return new ArrayList<CategoryResponse>();
		}
	}

	private List<CategoryResponse> convertEntityToResponseList(List<Category> categories) {
		final ArrayList<CategoryResponse> categoryResponses = new ArrayList<CategoryResponse>();

		categories.forEach(row -> categoryResponses.add(CategoryTransformer.convertEntityToResponse(row)));

		return categoryResponses;
	}

	@Override
	public CategoryResponse createCategory(CategoryRequest categoryRequest) {

		Category category = CategoryTransformer.convertRequestToEntity(new Category(), categoryRequest);

		return commitAndResponse(category);
	}

	@Override
	public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BadRequestException("Invalid category."));

		category = CategoryTransformer.convertRequestToEntity(category, categoryRequest);

		if (category.getCategories() != null) {
			for (Map.Entry<Long, Boolean> row : category.getCategories().entrySet()) {
				Category categoryTemp = categoryRepository.findById(row.getKey())
						.orElseThrow(() -> new BadRequestException("Invalid category."));
				if (categoryTemp.getCategories() != null && categoryTemp.getCategories().containsKey(category.getId()))
					throw new BadRequestException("Loop found");

			}
		}

		return commitAndResponse(category);
	}

	private CategoryResponse commitAndResponse(Category category) {
		category = categoryRepository.save(category);

		return CategoryTransformer.convertEntityToResponse(category);
	}

	@Override
	public DeleteResponse deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BadRequestException("Invalid category."));

		categoryRepository.delete(category);

		return new DeleteResponse(AppConstants.DELETE_MESSAGE);
	}
}
