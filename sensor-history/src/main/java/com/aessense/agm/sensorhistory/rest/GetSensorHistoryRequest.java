package com.aessense.agm.sensorhistory.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aessense.agm.sensorhistory.model.SensorType;

import lombok.Builder;
import lombok.Data;

/**
 * Pojo for encapsulating sensor history request arguments.
 * @author John Long
 *
 */
@Data
@Builder
public class GetSensorHistoryRequest {
	private long customerId;
	private int deviceId;
	private List<SensorType> sensorTypes = new ArrayList<>();
	private Date startDate;
	private Date endDate;
	private Date timeStamp;
	private String token;
}
