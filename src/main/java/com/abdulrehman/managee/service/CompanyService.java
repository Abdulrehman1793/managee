package com.abdulrehman.managee.service;

import com.abdulrehman.managee.payload.request.AddressRequest;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.AddressResponse;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
public interface CompanyService {

	public abstract String greetings();

	public abstract CompanyResponse findById(Long id);

	public abstract CompanyResponse findByDisplayName(String displayName);

	public abstract CompanyResponse findByEmail(String email);

	public abstract CompanyResponse createCompany(CompanyRequest companyRequest);

	public abstract CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

	public abstract CompanyResponse addCompanyAddress(Long companyId, AddressRequest addressRequest);

	public abstract CompanyResponse updateCompanyAddress(Long companyId, Long addressId, AddressRequest addressRequest);

	public abstract DeleteResponse deleteCompany(Long id);

}
