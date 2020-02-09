package com.abdulrehman.managee.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.abdulrehman.managee.model.audit.DateAudit;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 6, 2020
 */
@Entity
public class UserParam extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8417458025122273372L;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
