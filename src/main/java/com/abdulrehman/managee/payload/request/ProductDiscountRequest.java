package com.abdulrehman.managee.payload.request;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Setter
@Getter
public class ProductDiscountRequest {
	private String message;
	private int discount;
	private Instant startDate;
	private Instant endDate;
}
