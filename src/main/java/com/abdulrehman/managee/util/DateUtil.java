package com.abdulrehman.managee.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import com.abdulrehman.managee.exception.AppException;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 28, 2020
 */
public class DateUtil {

	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT);
	private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern(AppConstants.DATETIME_FORMAT);

	public static boolean isBetween(Instant value, Instant start, Instant end) throws Exception {
		if (start.isAfter(end) || end.isBefore(start))
			throw new AppException(ExceptionMessage.START_END_DATE_INVALID);
		if (value.isAfter(start) && value.isBefore(end))
			return true;
		return false;
	}

	public static Instant convertDateTimeStringToInstant(String timestamp) {
		TemporalAccessor temporalAccessor = dateTimeFormatter.parse(timestamp);
		LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
		return Instant.from(zonedDateTime);
	}

	public static String convertDateTimeInstantToString(Instant instant) {
		return dateTimeFormatter.withZone(ZoneId.systemDefault()).format(instant);
	}

	public static Instant convertDateStringToInstant(String timestamp) {
		TemporalAccessor temporalAccessor = dateFormatter.parse(timestamp);
//		LocalDate localDateTime = LocalDate.from(temporalAccessor);
		return Instant.from(temporalAccessor);
//		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
//		return Instant.from(zonedDateTime);
	}

	public static String convertDateInstantToString(Instant instant) {
		return dateFormatter.withZone(ZoneId.systemDefault()).format(instant);
	}
}
