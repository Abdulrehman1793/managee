package com.abdulrehman.managee.transformer;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.abdulrehman.managee.model.Address;
import com.abdulrehman.managee.payload.request.AddressRequest;
import com.abdulrehman.managee.payload.response.AddressResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 23, 2020
 */
public class AddressTransformer {
	public static AddressResponse responseFromAddress(Address address) {

		AddressResponse addressResponse = new AddressResponse();

		addressResponse.setCity(address.getCity());
		addressResponse.setContactName(address.getContactName());
		addressResponse.setCountry(address.getCountry());
		addressResponse.setCreatedAt(address.getCreatedAt());
		addressResponse.setId(address.getId());
		addressResponse.setLandmark(address.getLandmark());
		addressResponse.setLine1(address.getLine1());
		addressResponse.setLine2(address.getLine2());
		addressResponse.setLine3(address.getLine3());
		addressResponse.setPhone(address.getPhone());
		addressResponse.setPostal(address.getPostal());
		addressResponse.setState(address.getState());
		addressResponse.setUpdatedAt(address.getUpdatedAt());

		return addressResponse;
	}

	public static Address addressFromRequest(AddressRequest addressRequest, Address address) {

		address.setCity(addressRequest.getCity());
		address.setContactName(addressRequest.getContactName());
		address.setCountry(addressRequest.getCountry());
		address.setLandmark(addressRequest.getLandmark());
		address.setLine1(addressRequest.getLine1());
		address.setLine2(addressRequest.getLine2());
		address.setLine3(addressRequest.getLine3());
		address.setPhone(addressRequest.getPhone());
		address.setPostal(addressRequest.getPostal());
		address.setState(addressRequest.getState());

		return address;
	}

	public static Set<Address> addressesFromCompanyRequest(Set<AddressRequest> addressRequests) {

		if (addressRequests == null || addressRequests.size() == 0)
			return null;

		Set<Address> addresses = new HashSet<>();

		addresses = addressRequests.stream().map(request -> {
			return AddressTransformer.addressFromRequest(request, new Address());
		}).collect(Collectors.toSet());

		return addresses;
	}

	public static Set<AddressResponse> addressesResponseFromAddressses(Set<Address> addresses) {
		if (addresses == null || addresses.size() == 0)
			return null;

		Set<AddressResponse> addressesResponses = new HashSet<>();

		addressesResponses = addresses.stream().map(address -> {
			return AddressTransformer.responseFromAddress(address);
		}).collect(Collectors.toSet());

		return addressesResponses;
	}
}
