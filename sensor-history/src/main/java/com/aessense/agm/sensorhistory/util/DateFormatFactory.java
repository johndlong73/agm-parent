package com.aessense.agm.sensorhistory.util;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class DateFormatFactory {

	private SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
	
	public SimpleDateFormat getDateFormat() {
		return this.formatter;
	}
	
}
