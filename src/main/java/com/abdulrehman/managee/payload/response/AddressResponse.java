package com.abdulrehman.managee.payload.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 23, 2020
 */
@Getter
@Setter
public class AddressResponse {
	private Long id;

	private String contactName;

	private String line1;
	private String line2;
	private String line3;

	private String landmark;
	private String postal;

	private String city;
	private String state;

	private String country;

	private Long phone;

	private Instant createdAt;
	private Instant updatedAt;
}
