package com.abdulrehman.managee.transformer;

import com.abdulrehman.managee.model.Company;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
public class CompanyTransformer {

	public static CompanyResponse responseFromCompany(Company company) {

		CompanyResponse companyResponse = new CompanyResponse();

		companyResponse.setDisplayName(company.getDisplayName());
		companyResponse.setEmail(company.getEmail());
		companyResponse.setEmailVerified(company.isEmailVerified());
		companyResponse.setId(company.getId());
		companyResponse.setPhone(company.getPhone());
		companyResponse.setPhoneVerified(company.isPhoneVerified());
		companyResponse.setType(company.getType());
		companyResponse.setCode(company.getCode());

		companyResponse
				.setAddressesResponseCustom(AddressTransformer.addressesResponseFromAddressses(company.getAddresses()));

		return companyResponse;
	}

	public static Company companyFromRequest(CompanyRequest companyRequest, Company company) {

		company.setDisplayName(companyRequest.getDisplayName());
		company.setEmail(companyRequest.getEmail());
		company.setId(companyRequest.getId());
		company.setPhone(companyRequest.getPhone());
		company.setType(companyRequest.getType());
		company.setCode(companyRequest.getCode());

		return company;
	}

}
