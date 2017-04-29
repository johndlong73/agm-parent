package com.aessense.agm.sensorhistory.rest;

import java.math.BigDecimal;
import java.util.Date;

import com.aessense.agm.sensorhistory.config.ConfigConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorHistoryDto {
	private int deviceId;
	private String sensorType;
	private int sensorIndex;
	private BigDecimal target;
	private BigDecimal value;
	
	@JsonFormat(pattern=ConfigConstants.DATE_FORMAT)
	private Date createdAt;
}
