package com.abdulrehman.managee.config;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 31, 2020
 */
//@Configuration
public class JacksonConfig {
	private static final String dateFormat = "dd-MMM-yyy";

	private static final String dateTimeFormat = "dd-MMM-yyyy HH:mm:ss";

	@Bean
	@ConditionalOnProperty(value = "spring.jackson.date-format", matchIfMissing = true, havingValue = "none")
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return new Jackson2ObjectMapperBuilderCustomizer() {

			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.simpleDateFormat(dateTimeFormat);
				jacksonObjectMapperBuilder
						.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
				jacksonObjectMapperBuilder
						.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			}
		};

	}
}
