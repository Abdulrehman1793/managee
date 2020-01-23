package com.abdulrehman.managee.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.abdulrehman.managee.model.CompanyType;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 2, 2020
 */
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the type
	 */
	public CompanyType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CompanyType type) {
		this.type = type;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
