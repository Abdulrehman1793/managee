package com.abdulrehman.managee.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Address;
import com.abdulrehman.managee.repository.AddressRepository;
import com.abdulrehman.managee.service.AddressService;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 23, 2020
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findById(Long id) {

		Optional<Address> optional = addressRepository.findById(id);

		return validateGetAddress(optional);
	}

	private Address validateGetAddress(Optional<Address> optional) {
		optional.orElseThrow(() -> new BadRequestException("No address found."));

		return optional.get();
	}

}
