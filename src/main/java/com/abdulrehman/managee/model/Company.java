package com.abdulrehman.managee.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.abdulrehman.managee.model.audit.UserDateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Entity
@Setter
@Getter
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

	@JoinTable(name = "company_address_lnk", joinColumns = @JoinColumn(name = "comp_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "add_id", referencedColumnName = "id"))
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> addresses;

	public void addAddress(Address address) {
		if (this.addresses == null)
			this.addresses = new HashSet<Address>();
		this.addresses.add(address);
	}
}
