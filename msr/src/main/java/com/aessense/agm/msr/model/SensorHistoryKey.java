package com.aessense.agm.msr.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Custom key for sensor history since it has no natural primary key.
 * @author jlon14
 *
 */
@Data
public class SensorHistoryKey implements Serializable {
	private int deviceId;
	private int type;
	private int sensorIndex;
	private Date created;
}
