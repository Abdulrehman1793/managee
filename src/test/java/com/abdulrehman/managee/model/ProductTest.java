package com.abdulrehman.managee.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.abdulrehman.managee.util.DateUtil;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
class ProductTest {

	Product product;
	ProductPriceHistory productHistory1;
	ProductPriceHistory productHistory2;
	ProductPriceHistory productHistory3;

	Set<ProductPriceHistory> productHistories = new HashSet<>();

	@BeforeEach
	void setup() {
		product = new Product("Product1", "Product description 01");
		productHistory1 = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("01-01-2020 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("31-01-2020 23:59:59"));
		productHistory2 = new ProductPriceHistory(new BigInteger("20"), new BigDecimal("180.00"),
				DateUtil.convertDateTimeStringToInstant("01-02-2020 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("25-02-2020 23:59:59"));
		productHistory3 = new ProductPriceHistory(new BigInteger("25"), new BigDecimal("185.00"),
				DateUtil.convertDateTimeStringToInstant("26-02-2020 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("30-04-2020 23:59:59"));
	}

	@DisplayName("should add product histoty")
	@Test
	void addProductHistoryLenghtTest() {

		product.addproductPriceHistory(productHistory1);

		assertEquals(1, product.getProductPriceHistories().size());
		assertEquals(product, productHistory1.getProduct());
	}

	@Test
	void addProductHistoryTest() {

		ProductPriceHistory productHistoryTemp = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("01-01-2020 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("31-01-2020 23:59:59"));
		product.addproductPriceHistory(productHistory1);
		product.addproductPriceHistory(productHistory1);
		product.addproductPriceHistory(productHistoryTemp);

		assertEquals(2, product.getProductPriceHistories().size());
	}

	void isProductHistoryExistsSetup() {

		product.addproductPriceHistory(productHistory1);
		product.addproductPriceHistory(productHistory2);
		product.addproductPriceHistory(productHistory3);
	}

	@Test
	void isPHExists() throws Exception {
		isProductHistoryExistsSetup();

		ProductPriceHistory productHistoryTemp = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("04-01-2020 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("28-01-2020 23:59:59"));

		boolean exists = product.isProductHistoryExists(productHistoryTemp);

		assertEquals(true, exists);
	}

	@Test
	void isPHExistsONNullEndDate() throws Exception {
		isProductHistoryExistsSetup();

		ProductPriceHistory productHistoryTemp = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("04-01-2020 00:00:01"));

		boolean exists = product.isProductHistoryExists(productHistoryTemp);

		assertEquals(true, exists);
	}

	@Test
	void isPHExistsO() throws Exception {
		isProductHistoryExistsSetup();

		ProductPriceHistory productHistoryTemp = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("04-01-2019 00:00:01"),
				DateUtil.convertDateTimeStringToInstant("28-01-2019 23:59:59"));

		boolean exists = product.isProductHistoryExists(productHistoryTemp);

		assertEquals(false, exists);
	}

	@Test
	void isPHExistsO1() throws Exception {
		isProductHistoryExistsSetup();

		productHistory3 = new ProductPriceHistory(new BigInteger("25"), new BigDecimal("185.00"),
				DateUtil.convertDateTimeStringToInstant("01-05-2020 00:00:01"));
		product.addproductPriceHistory(productHistory1);

		ProductPriceHistory productHistoryTemp = new ProductPriceHistory(new BigInteger("10"), new BigDecimal("100.00"),
				DateUtil.convertDateTimeStringToInstant("04-08-2020 00:00:01"));

		boolean exists = product.isProductHistoryExists(productHistoryTemp);

		assertEquals(false, exists);
	}
}
