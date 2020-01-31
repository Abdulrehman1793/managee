package com.abdulrehman.managee.payload.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 30, 2020
 */
@Getter
@Setter
public class ProductPriceHistoryResponse {
	private Long id;

	private String message;
	private BigInteger quantity;
	private BigDecimal amount;
	private Instant startDate;
	private Instant endDate;
}
