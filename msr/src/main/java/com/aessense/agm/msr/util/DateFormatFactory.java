package com.aessense.agm.msr.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.aessense.agm.msr.config.ConfigConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Autowireable factory for getting a date formatter.  Makes date formatting
 * uniform throughout the application.
 * 
 * @author John Long
 *
 */
@Slf4j
@Component
public class DateFormatFactory {

	private SimpleDateFormat formatter = new SimpleDateFormat(ConfigConstants.DATE_FORMAT, Locale.US);
	
	public SimpleDateFormat getDateFormat() {
		return this.formatter;
	}
	
}
