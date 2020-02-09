package com.abdulrehman.managee.model;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.abdulrehman.managee.model.audit.UserDateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
@Entity
@Setter
@Getter
public class Category extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2992354696083109341L;

	private String name;
	private String description;

	private int displayOrder;

	@Lob
	@Column(length = 100000)
	private LinkedHashMap<Long, Boolean> categories;
	@Lob
	@Column(length = 100000)
	private LinkedHashMap<Long, Boolean> products;

	private boolean active;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
}
