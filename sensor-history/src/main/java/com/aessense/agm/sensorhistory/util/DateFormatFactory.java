package com.aessense.agm.sensorhistory.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.aessense.agm.sensorhistory.config.ConfigConstants;

/**
 * Autowireable factory for getting a date formatter.  Makes date formatting
 * uniform throughout the application.
 * 
 * @author John Long
 *
 */
@Component
public class DateFormatFactory {

	private SimpleDateFormat formatter = new SimpleDateFormat(ConfigConstants.DATE_FORMAT, Locale.US);
	
	/**
	 * Returns a date formatter.
	 */
	public SimpleDateFormat getDateFormat() {
		return this.formatter;
	}
	
}
