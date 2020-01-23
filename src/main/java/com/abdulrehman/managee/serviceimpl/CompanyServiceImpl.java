package com.abdulrehman.managee.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulrehman.managee.exception.BadRequestException;
import com.abdulrehman.managee.model.Company;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.repository.CompanyRepository;
import com.abdulrehman.managee.service.CompanyService;
import com.abdulrehman.managee.transformer.CompanyTransformer;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public String greetings() {
		return "Company service";
	}

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

		company = companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
	}

	@Override
	public CompanyResponse updateCompany(CompanyRequest companyRequest) {

		Company company = validateGetCompany(companyRepository.findById(companyRequest.getId()));

		company = CompanyTransformer.companyFromRequest(companyRequest, company);

		company = companyRepository.save(company);

		return CompanyTransformer.responseFromCompany(company);
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
}
