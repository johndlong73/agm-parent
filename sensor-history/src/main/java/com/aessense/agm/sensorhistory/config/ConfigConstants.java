package com.aessense.agm.sensorhistory.config;

/**
 * Global constants
 * @author John Long
 *
 */
public class ConfigConstants {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String API_VERSION="v1.0";
	public static final String SENSOR_HISTORY_URL= "/" + API_VERSION + "/{customerId}/sensorHistory";
	public static final String FILE_ENCODING="UTF-8";
	
	private ConfigConstants() {
		// Hidden default constructor
	}	
}
