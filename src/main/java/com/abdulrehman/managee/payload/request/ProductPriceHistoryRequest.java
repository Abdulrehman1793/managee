package com.abdulrehman.managee.payload.request;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Setter
@Getter
public class ProductPriceHistoryRequest {
	private String message;
	private BigInteger quantity;
	private BigDecimal amount;

	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss", timezone = "UTC")
	private Instant startDate;
	private Instant endDate;
}
