package com.abdulrehman.managee.service;

import com.abdulrehman.managee.model.Address;
import com.abdulrehman.managee.payload.response.DeleteResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 23, 2020
 */
public interface AddressService {
	public abstract Address findById(Long id);

	public abstract DeleteResponse deleteById(Long id);
}
