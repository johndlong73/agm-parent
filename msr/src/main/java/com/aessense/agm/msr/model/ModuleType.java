package com.aessense.agm.msr.model;

/**
 * Enumeration of agm modules.
 * @author John Long
 *
 */
public enum ModuleType {
	MULTI_SENSOR_REPORT (1l);
	
	private final long value;
	
	ModuleType(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return this.value;
	}
}
