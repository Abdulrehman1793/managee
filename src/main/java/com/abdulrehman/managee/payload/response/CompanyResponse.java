package com.abdulrehman.managee.payload.response;

import java.util.Set;

import com.abdulrehman.managee.model.CompanyType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Getter
@Setter
public class CompanyResponse {
	private Long id;
	private String displayName;
	private CompanyType type;
	private String email;
	private boolean isEmailVerified;
	private String phone;
	private boolean isPhoneVerified;
	private Set<AddressResponse> addressResponses;

	private String code;

	public CompanyResponse() {
		super();
	}

	public CompanyResponse(Long id, String displayName, CompanyType type, String email, boolean isEmailVerified,
			String phone, boolean isPhoneVerified, String code) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.type = type;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.phone = phone;
		this.isPhoneVerified = isPhoneVerified;
		this.code = code;
	}

	public void setAddressesResponseCustom(Set<AddressResponse> addressResponses) {
		this.addressResponses = addressResponses;
	}
}
