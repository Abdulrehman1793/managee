package com.abdulrehman.managee.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Address;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.repository.AddressRepository;
import com.abdulrehman.managee.service.AddressService;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 23, 2020
 */
@Service
public class AddressServiceImpl implements AddressService {
	// TODO: Properly comment on each line of code and description on method
	// Created by Khan Abdulrehman on Jan 24, 2020 8:22:59 AM
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findById(Long id) {
		return validateGetAddress(addressRepository.findById(id));
	}

	@Override
	public DeleteResponse deleteById(Long id) {

		Address address = validateGetAddress(addressRepository.findById(id));

		addressRepository.delete(address);

		return new DeleteResponse("Record deleted succeddfully.");
	}

	private Address validateGetAddress(Optional<Address> optional) {
		optional.orElseThrow(() -> new BadRequestException("No address found."));

		return optional.get();
	}

}
