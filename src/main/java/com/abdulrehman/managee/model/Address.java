package com.abdulrehman.managee.model;

import javax.persistence.Entity;

import com.abdulrehman.managee.model.audit.DateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 21, 2020
 */
@Entity
@Getter
@Setter
public class Address extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1833894360505554890L;

	private String contactName;

	// Type of address (Home, Office, Head Office etc)
	private String type;

	private String line1;
	private String line2;
	private String line3;
	private String landmark;
	private String postal;
	private String city;
	private String state;

	private String country;

	private Long phone;

}
