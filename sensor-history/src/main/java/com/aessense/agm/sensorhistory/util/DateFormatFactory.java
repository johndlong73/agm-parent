package com.aessense.agm.sensorhistory.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.aessense.agm.sensorhistory.config.ConfigConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateFormatFactory {

	private SimpleDateFormat formatter = new SimpleDateFormat(ConfigConstants.DATE_FORMAT, Locale.US);
	
	@PostConstruct
	private void setTimeZone() {
		log.info("Setting timezone for date formatter: " + TimeZone.getTimeZone("UTC"));
		this.formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public SimpleDateFormat getDateFormat() {
		return this.formatter;
	}
	
}
