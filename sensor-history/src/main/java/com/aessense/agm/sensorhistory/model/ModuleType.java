package com.aessense.agm.sensorhistory.model;

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
