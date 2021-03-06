package com.abdulrehman.managee.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface AppConstants {
	String DELETE_MESSAGE = "Record deleted succesfully.";

	String DATE_FORMAT = "dd-MM-yyyy";
	String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	Integer DEFAULT_PAGE_NUMBER = 0;
	Integer DEFAULT_PAGE_SIZE = 30;

	int MAX_PAGE_SIZE = 50;

	// Product price history string constant
	String PPH_MESSAGE = "Auto Generated";
	BigInteger PPH_QUANTITY = BigInteger.ONE;
	BigDecimal PPH_AMOUNT = BigDecimal.ONE;
}
