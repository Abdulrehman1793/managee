package com.abdulrehman.managee.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.abdulrehman.managee.model.CompanyType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 2, 2020
 */
@Getter
@Setter
public class CompanyRequest {
	private Long id;

	@NotBlank(message = "Display name is required.")
	private String displayName;

	private CompanyType type;

	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid email address."
//	, regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$"
	)
	private String email;

	private String phone;

	private String qrCode;

	private String code;

	private Set<AddressRequest> addressRequests;

	public CompanyRequest() {
		super();
	}

	public CompanyRequest(@NotBlank(message = "Display name is required.") String displayName, String type,
			@NotBlank(message = "Email is required.") @Email(message = "Invalid email address.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$") String email,
			String phone) {
		super();
		this.displayName = displayName;
		this.email = email;
		this.phone = phone;
	}

	public CompanyRequest(@NotBlank(message = "Display name is required.") String displayName, CompanyType type,
			@NotBlank(message = "Email is required.") @Email(message = "Invalid email address.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$") String email,
			String phone, String qrCode) {
		super();
		this.displayName = displayName;
		this.type = type;
		this.email = email;
		this.phone = phone;
	}

	public CompanyRequest(Long id, @NotBlank(message = "Display name is required.") String displayName,
			CompanyType type,
			@NotBlank(message = "Email is required.") @Email(message = "Invalid email address.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$") String email,
			String phone, String qrCode, String code) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.type = type;
		this.email = email;
		this.phone = phone;
		this.qrCode = qrCode;
		this.code = code;
	}
}
