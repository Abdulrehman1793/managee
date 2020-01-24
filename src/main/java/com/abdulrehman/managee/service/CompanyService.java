package com.abdulrehman.managee.service;

import com.abdulrehman.managee.payload.request.AddressRequest;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
public interface CompanyService {

	/**
	 * Get company response by company id.
	 */
	public abstract CompanyResponse findById(Long id);

	/**
	 * Get company response by company display name.
	 */
	public abstract CompanyResponse findByDisplayName(String displayName);

	/**
	 * Get company response by company email address.
	 */
	public abstract CompanyResponse findByEmail(String email);

	/**
	 * Create/Add New Company
	 */
	public abstract CompanyResponse createCompany(CompanyRequest companyRequest);

	/**
	 * Update existing company
	 */
	public abstract CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

	/**
	 * Adding new address for company
	 */
	public abstract CompanyResponse addCompanyAddress(Long companyId, AddressRequest addressRequest);

	/**
	 * Updating existing company address.
	 */
	public abstract CompanyResponse updateCompanyAddress(Long companyId, Long addressId, AddressRequest addressRequest);

	/**
	 * Deleting single address record from company.
	 */
	public abstract CompanyResponse deleteCompanyAddress(Long companyId, Long addressId);

	/**
	 * Deleting company and all of its dependencies.
	 */
	public abstract DeleteResponse deleteCompany(Long id);

}
