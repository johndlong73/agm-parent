package com.aessense.agm.sensorhistory.rest;

import java.math.BigDecimal;
import java.util.Date;

import com.aessense.agm.sensorhistory.config.ConfigConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * Pojo for sensor history data.
 * @author John Long
 *
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorHistoryDto {
	private int deviceId;
	private String sensorType;
	private int sensorIndex;
	private BigDecimal target;
	private BigDecimal value;
	
	@JsonFormat(pattern=ConfigConstants.DATE_FORMAT)
	private Date createdAt;
}
