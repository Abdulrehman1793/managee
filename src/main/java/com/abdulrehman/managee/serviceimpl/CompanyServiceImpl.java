package com.abdulrehman.managee.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.AppException;
import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Address;
import com.abdulrehman.managee.model.Company;
import com.abdulrehman.managee.model.RoleName;
import com.abdulrehman.managee.model.User;
import com.abdulrehman.managee.payload.request.AddressRequest;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.repository.CompanyRepository;
import com.abdulrehman.managee.security.UserPrincipal;
import com.abdulrehman.managee.service.AddressService;
import com.abdulrehman.managee.service.AuthService;
import com.abdulrehman.managee.service.CompanyService;
import com.abdulrehman.managee.transformer.AddressTransformer;
import com.abdulrehman.managee.transformer.CompanyTransformer;
import com.abdulrehman.managee.util.AppUtils;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	// TODO: Properly comment on each line of code and description on method
	// Created by Khan Abdulrehman on Jan 24, 2020 8:22:59 AM
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AuthService authService;

	@Override
	public CompanyResponse findByDisplayName(String displayName) {
		Optional<Company> optional = companyRepository.findByDisplayName(displayName);

		return CompanyTransformer.responseFromCompany(validateGetCompany(optional));
	}

	@Override
	public CompanyResponse findById(Long id) {
		Optional<Company> optional = companyRepository.findById(id);

		return CompanyTransformer.responseFromCompany(validateGetCompany(optional));
	}

	@Override
	public CompanyResponse findByEmail(String email) {
		Optional<Company> optional = companyRepository.findByEmail(email);

		return CompanyTransformer.responseFromCompany(validateGetCompany(optional));
	}

	@Override
	public CompanyResponse createCompany(CompanyRequest companyRequest) {

		Company company = CompanyTransformer.companyFromRequest(companyRequest, new Company());

		String code = UUID.randomUUID().toString();

		while (companyRepository.findByCode(code).isPresent()) {
			code = UUID.randomUUID().toString();
		}

		company.setCode(code);

		Set<Address> addresses = AddressTransformer.addressesFromCompanyRequest(companyRequest.getAddressRequests());
		if (addresses != null)
			company.setAddresses(addresses);

		User user = authService.getCurrentUser();

		// Company should have one owner.
		List<Company> companies = companyRepository.findAllCompanyByUserId(user.getId());
		if (companies.size() > 0)
			throw new AppException("The user have already registered for other company.");

		// Adding user.
		company.addUser(user);

		// Update role of current user to ROLE_OWNER
		authService.updateCurrentUserRole(RoleName.ROLE_OWNER);

		company = companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
	}

	@Override
	public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {

		Company company = validateGetCompany(companyRepository.findById(id));

		company = CompanyTransformer.companyFromRequest(companyRequest, company);

		company = companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
	}

	@Override
	public CompanyResponse addCompanyAddress(Long companyId, AddressRequest addressRequest) {
		Company company = validateGetCompany(companyRepository.findById(companyId));

		company.addAddress(AddressTransformer.addressFromRequest(addressRequest, new Address()));

		company = companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
	}

	@Override
	public CompanyResponse updateCompanyAddress(Long companyId, Long addressId, AddressRequest addressRequest) {
		Company company = validateGetCompany(companyRepository.findById(companyId));

		// Get address record from database using AddressService
		Address address = addressService.findById(addressId);

		// Updated address entity as user request.
		address = AddressTransformer.addressFromRequest(addressRequest, address);

		// Merging address to the company instance
		company.addAddress(address);

		// Commit and return response
		return commitAndAcknowledgeCompany(company);
	}

	@Override
	public CompanyResponse deleteCompanyAddress(Long companyId, Long addressId) {

		// Get company record by id
		Company company = validateGetCompany(companyRepository.findById(companyId));

		// Remove address by id from Set<Address>.
		// It will only delete mapping from Jointable(company_address_lnk) but address
		// data will remain in table, So we need to delete from address table too.
		company.removeAddressById(addressId);

		// Remove address record from address table
		addressService.deleteById(addressId);

		// Commit and return response
		return commitAndAcknowledgeCompany(company);
	}

	@Override
	public DeleteResponse deleteCompany(Long id) {
		Company company = validateGetCompany(companyRepository.findById(id));
		companyRepository.delete(company);

		return new DeleteResponse("Record deleted succeddfully.");
	}

	private Company validateGetCompany(Optional<Company> optional) {
		optional.orElseThrow(() -> new BadRequestException("No company found."));

		return optional.get();
	}

	private CompanyResponse commitAndAcknowledgeCompany(Company company) {
		companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
	}
}
