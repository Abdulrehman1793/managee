package com.abdulrehman.managee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.abdulrehman.managee.model.audit.UserDateAudit;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Entity
public class Company extends UserDateAudit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String displayName;

	@Enumerated(EnumType.STRING)
	private CompanyType type;

	private String email;
	private boolean isEmailVerified;
	private String phone;
	private boolean isPhoneVerified;
	private String qrCode;

	@Column(updatable = false, nullable = false, unique = true)
	private String code;

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
