package com.abdulrehman.managee.service;

import com.abdulrehman.managee.payload.request.CompanyRequest;
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

	public abstract DeleteResponse deleteCompany(Long id);

}
