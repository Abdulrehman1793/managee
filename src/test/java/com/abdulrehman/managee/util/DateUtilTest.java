package com.abdulrehman.managee.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.abdulrehman.managee.exception.AppException;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 28, 2020
 */
class DateUtilTest {

	Instant value;
	Instant start;
	Instant end;

	@BeforeEach
	public void setUp() {
		start = DateUtil.convertDateTimeStringToInstant("01-01-2020 00:00:01");
		end = DateUtil.convertDateTimeStringToInstant("30-01-2020 23:59:59");
		value = DateUtil.convertDateTimeStringToInstant("31-01-2020 10:30:43");

	}

	@Test
	@DisplayName("Date is between start and end date.")
	void isBetweenTest() throws Exception {
		Instant value = DateUtil.convertDateTimeStringToInstant("28-01-2020 10:30:43");

		boolean isBetween = DateUtil.isBetween(value, start, end);

		assertEquals(true, isBetween);
	}

	@Test
	@DisplayName("Date is not between start and end date.")
	void isOutsideTest() throws Exception {
		Instant value = DateUtil.convertDateTimeStringToInstant("31-01-2020 10:30:43");

		boolean isBetween = DateUtil.isBetween(value, start, end);

		assertEquals(false, isBetween);
	}

	@Test
	@DisplayName("should return false - date is equal to start date.")
	void isEqualToStartTest() throws Exception {
		Instant value = start;

		boolean isBetween = DateUtil.isBetween(value, start, end);

		assertEquals(false, isBetween);
	}

	@Test
	@DisplayName("should return false - date is equal to end date.")
	void isEqualToEndTest() throws Exception {
		Instant value = end;

		boolean isBetween = DateUtil.isBetween(value, start, end);

		assertEquals(false, isBetween);
	}

	@Test
	@DisplayName("should throw error - start date is greater than end date.")
	void isStartDateAfterEndTest() throws Exception {

		Instant start = end.plusMillis(1);

		Throwable throwable = assertThrows(AppException.class, () -> DateUtil.isBetween(value, start, end));
		assertEquals(ExceptionMessage.START_END_DATE_INVALID, throwable.getMessage());
	}
}
