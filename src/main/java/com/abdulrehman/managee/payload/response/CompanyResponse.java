package com.abdulrehman.managee.payload.response;

import com.abdulrehman.managee.model.CompanyType;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
public class CompanyResponse {
	private Long id;
	private String displayName;
	private CompanyType type;
	private String email;
	private boolean isEmailVerified;
	private String phone;
	private boolean isPhoneVerified;

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
	 * @return the isEmailVerified
	 */
	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	/**
	 * @param isEmailVerified the isEmailVerified to set
	 */
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
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
	 * @return the isPhoneVerified
	 */
	public boolean isPhoneVerified() {
		return isPhoneVerified;
	}

	/**
	 * @param isPhoneVerified the isPhoneVerified to set
	 */
	public void setPhoneVerified(boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
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
