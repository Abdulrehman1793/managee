package com.abdulrehman.managee.payload.request;

import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
@Getter
@Setter
public class CategoryRequest {
	private String name;
	private String description;

	private int displayOrder;

	private LinkedHashMap<Long, Boolean> categories;
	private LinkedHashMap<Long, Boolean> products;

	private boolean active;
}
