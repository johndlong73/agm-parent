package com.aessense.agm.msr.model;

/**
 * Enumeration to map human friendly Sensor/Actuator names to database id's and indexes.
 */
public enum SensorType {
	
	//SensorName (id,index)
	TEMPERATURE (1,0),
	LIGHT (2,0),
	HUMIDITY (3,0),
	WEIGHT_0 (4,0),
	WEIGHT_1 (4,1),
	WEIGHT_2 (4,2),
	WEIGHT_3 (4,3),
	WEIGHT_4 (4,4),
	WEIGHT_5 (4,5),
	WEIGHT_6 (4,6),
	WATER_LEVEL (5,0),
	CARBON_DIOXIDE (7,0),
	PH (8,0),
	DISSOLVED_OXYGEN (9,0),
	CARBON_MONOXIDE (10,0),
	OXYGEN (11,0),
	TDS (12,0),
	WATER_TEMPERATURE (13,0),
	TOTAL_CONDUCTIVITY (14,0),
	WATER_PUMP (16,0),
	CARBON_DIOXIDE_PUMP (17,0), 
	LED_PANELS (18,0),
	FAN (19,0),
	HEAT_UNIT (20,0),
	COOL_UNIT (21,0),
	MISTER (32,0),
	EXHAUST_FAN (33,0),
	TRAFFIC_LIGHT (34,0),
	STEPPER_0 (35,0),
	STEPPER_1 (35,1),
	STEPPER_2 (35,2),
	STEPPER_3 (35,3),
	STEPPER_4 (35,4),
	STEPPER_5 (35,5),
	STEPPER_6 (35,6),
	SOLENOID_DRIVER (36,0),
	CIRCULATION_PUMP (37,0),
	LIGHT_MOVE_UP (39,0),
	LIGHT_MOVE_DOWN (40,0),
	WATER_VALVE (41,0),
	BOOSTER (42,0),
	AIR_CONDITIONER (43,0),
	HUMIDITY_CONDITIONER (44,0),
	WATER_DRAIN_PUMP (45,0),
	MIXER (46,0),
	LIGHT_SMART_RELAY (50,0),
	WATER_PRESSURE (112,0);
	
	private final int id;
	private final int index;
	
	SensorType(int id, int index) {
		this.id = id;
		this.index = index;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Given a sensor id and sensor index return the matching enum.
	 * @param id the sensory id
	 * @param index the sensor index
	 * @return
	 */
	public static SensorType fromValues(int id, int index) {
		SensorType result = null;
		for(SensorType s: SensorType.values()) {
			if(s.getId() == id && s.getIndex() == index) {
				result = s;
			}
		}
		return result;
	}
}
