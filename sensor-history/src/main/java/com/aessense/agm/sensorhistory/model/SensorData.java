package com.aessense.agm.sensorhistory.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorData {
	private long deviceId;
	private long sensorType;
	private long sensorIndex;
	private BigDecimal target;
	private BigDecimal value;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date createdAt;
}
