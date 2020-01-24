package com.abdulrehman.managee.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdulrehman.managee.payload.request.AddressRequest;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.service.CompanyService;
import com.abdulrehman.managee.util.AppUrls;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@RestController
@RequestMapping(AppUrls.COMPANY)
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/displayname/{displayName}")
	private CompanyResponse findByDisplayName(@NotNull @PathVariable(name = "displayName") String displayName) {
		return companyService.findByDisplayName(displayName);
	}

	@GetMapping("/email/{email}")
	private CompanyResponse findByEmail(@NotNull @PathVariable(name = "email") String email) {
		return companyService.findByEmail(email);
	}

	@GetMapping("/id/{id}")
	private CompanyResponse findByEmail(@NotNull @PathVariable(name = "id") Long id) {
		return companyService.findById(id);
	}

	@PostMapping("/create")
	private CompanyResponse createCompany(@Valid @RequestBody CompanyRequest companyRequest) {
		return companyService.createCompany(companyRequest);
	}

	@PostMapping("/update/{id}")
	private CompanyResponse updateCompany(@NotNull @PathVariable(name = "id") Long id,
			@RequestBody CompanyRequest companyRequest) {
		return companyService.updateCompany(id, companyRequest);
	}

	@PostMapping("/add/address/{id}")
	private CompanyResponse addCompanyAddress(@NotNull @PathVariable(name = "id") Long id,
			@RequestBody AddressRequest addressRequest) {
		return companyService.addCompanyAddress(id, addressRequest);
	}

	@PostMapping("/update/address/{companyId}/{addressId}")
	private CompanyResponse updateCompanyAddress(@NotNull @PathVariable(name = "addressId") Long addressId,
			@NotNull @PathVariable(name = "companyId") Long companyId, @RequestBody AddressRequest addressRequest) {
		return companyService.updateCompanyAddress(companyId, addressId, addressRequest);
	}

	@DeleteMapping("/delete/address/{companyId}/{addressId}")
	private CompanyResponse deleteCompanyAddress(@NotNull @PathVariable(name = "addressId") Long addressId,
			@NotNull @PathVariable(name = "companyId") Long companyId) {
		return companyService.deleteCompanyAddress(companyId, addressId);
	}

	@DeleteMapping("/delete/{id}")
	private DeleteResponse deleteCompany(@PathVariable long id) {
		return companyService.deleteCompany(id);
	}

}
