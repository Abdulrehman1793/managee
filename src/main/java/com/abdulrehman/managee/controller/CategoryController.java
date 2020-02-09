package com.abdulrehman.managee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdulrehman.managee.payload.request.CategoryRequest;
import com.abdulrehman.managee.payload.response.CategoryResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.service.CategoryService;
import com.abdulrehman.managee.util.AppConstants;
import com.abdulrehman.managee.util.AppUrls;
import com.abdulrehman.managee.util.AppUtils;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
@RestController
@RequestMapping(AppUrls.CATEGORY)
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = { "/page" })
	public List<CategoryResponse> findPage(@RequestParam(name = "no") Optional<Integer> pageNoOptional,
			@RequestParam(name = "size") Optional<Integer> pageSizeoptional,
			@RequestParam(name = "sort") Optional<List<String>> sortByOptional) {

		Integer pageNo = (pageNoOptional.isPresent()) ? pageNoOptional.get() : AppConstants.DEFAULT_PAGE_NUMBER;

		Integer pageSize = (pageSizeoptional.isPresent()) ? pageSizeoptional.get() : AppConstants.DEFAULT_PAGE_SIZE;

		Sort sort = AppUtils.requestParamSortBuilder(sortByOptional);

		return categoryService.findPage(pageNo, pageSize, sort);
	}

	@PostMapping("/create")
	public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
		return categoryService.createCategory(categoryRequest);
	}

	@PostMapping("/update/{categoryId}")
	public CategoryResponse updateCategory(@PathVariable("categoryId") Long categoryId,
			@RequestBody CategoryRequest categoryRequest) {
		return categoryService.updateCategory(categoryId, categoryRequest);
	}

	@DeleteMapping("/delete/{categoryId}")
	private DeleteResponse deleteCompany(@PathVariable long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}
}
