package com.abdulrehman.managee.payload.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
@Getter
@Setter
public class ProductDiscountResponse {
	private Long id;

	private String message;
	private int discount;
	private Instant startDate;
	private Instant endDate;
}
