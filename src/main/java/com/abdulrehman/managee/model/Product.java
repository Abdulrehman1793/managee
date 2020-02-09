package com.abdulrehman.managee.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.abdulrehman.managee.model.audit.UserDateAudit;
import com.abdulrehman.managee.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 28, 2020
 */
@Entity
@Setter
@Getter
public class Product extends UserDateAudit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3302622126606740203L;

	private String name;
	private String description;

	@Transient
	private BigInteger quantity;
	@Transient
	private BigDecimal mrp;
	@Transient
	private BigDecimal amount;

	// Globally available for all users
	private boolean isGlobal;

	@Enumerated(EnumType.STRING)
	private ProductUnit unit;

	private int displayOrder;
	private boolean active;
	private boolean isAvailable;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductPriceHistory> productPriceHistories;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductDiscount> productDiscounts;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public Product() {
	}

	public Product(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	/**
	 * Add product price history to the product entity for convenience of foreign
	 * key.
	 */
	public void addproductPriceHistory(ProductPriceHistory productPriceHistory) {
		if (productPriceHistory != null) {
			if (productPriceHistories == null)
				productPriceHistories = new HashSet<>();
			productPriceHistory.setProduct(this);
			productPriceHistories.add(productPriceHistory);
		}
	}

	/**
	 * 
	 * 
	 * Case 1: If pph start date is equal to set of start date return true. Case 2:
	 * If set<PH> end date is null. Case 2a: Only check if pph start dt is after
	 * set<PH> of start dt. case 3: if case 2 false: case 3a: Check if pph start dt
	 * is between Set<PH> start and end dt and if yes then return true. case 3b: To
	 * check if pph end dt i equal to Set<PH> end dt OR pph end date is between
	 * 
	 * @param pph ProductHistory
	 * @return To check whether pph product history already exists or not. If pph
	 *         start or end date exists between set of histories of start and end
	 *         date, it will return true.
	 * @throws Exception
	 */
	public boolean isProductHistoryExists(ProductPriceHistory pph) throws Exception {
		if (productPriceHistories == null)
			return false;

		for (ProductPriceHistory row : productPriceHistories) {
			// Case 1:
			if (row.getStartDate().equals(pph.getStartDate()))
				return true;
			// Case 3:
			if (row.getEndDate() != null) {
				// case 3a:
				if (DateUtil.isBetween(pph.getStartDate(), row.getStartDate(), row.getEndDate()))
					return true;
				// We already cover else part in case 3a:
				if (pph.getEndDate() != null) {
					// case 3b:
					if (row.getEndDate().equals(pph.getEndDate())
							|| DateUtil.isBetween(pph.getEndDate(), row.getStartDate(), row.getEndDate()))
						return true;
				}

			}
			// case 2:
			else {
				// Case 2a:
				if (pph.getStartDate().isAfter(row.getStartDate()))
					return true;
			}
		}

		return false;
	}

	public void addProductDiscount(ProductDiscount productDisount) {
		if (productDisount != null) {
			if (productDiscounts == null)
				productDiscounts = new HashSet<>();
			productDisount.setProduct(this);
			productDiscounts.add(productDisount);
		}
	}

	/**
	 * Max start date of history will return.Or return null if there are no price
	 * history available for this product.
	 */
	public ProductPriceHistory getProductPriceHistory() {
		ProductPriceHistory productPriceHistory = null;

		if (productPriceHistories == null)
			return productPriceHistory;

		Set<ProductPriceHistory> histories = productPriceHistories.stream().filter(row -> row.getEndDate() == null)
				.collect(Collectors.toSet());

		if (histories.size() == 0)
			return productPriceHistory;
		else if (histories.size() == 1)
			return histories.iterator().next();
		else if (histories.size() > 1) {
			List<ProductPriceHistory> lists = productPriceHistories.stream().collect(Collectors.toList());
			Collections.sort(lists, Comparator.comparing(ProductPriceHistory::getStartDate));
			productPriceHistory = lists.get(lists.size() - 1);
		}

		return productPriceHistory;
	}

	public ProductDiscount getProductDiscount() {
		ProductDiscount productDiscount = null;

		if (productDiscounts == null)
			return productDiscount;

		Set<ProductDiscount> discounts = productDiscounts.stream().filter(row -> row.getEndDate() == null)
				.collect(Collectors.toSet());

		if (discounts.size() == 0)
			return productDiscount;
		else if (discounts.size() == 1)
			return discounts.iterator().next();
		else if (discounts.size() > 1) {
			List<ProductDiscount> lists = productDiscounts.stream().collect(Collectors.toList());
			Collections.sort(lists, Comparator.comparing(ProductDiscount::getStartDate));
			productDiscount = lists.get(lists.size() - 1);
		}

		return productDiscount;
	}
}
