package com.aessense.agm.sensorhistory.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SensorHistoryKey implements Serializable {
	private int deviceId;
	private int type;
	private int sensorIndex;
	private Date created;
}
