package com.aessense.agm.msr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity for sensor_history table.
 * @author John Long
 *
 */
@Data
@Entity
@IdClass(SensorHistoryKey.class)
@Table(name="sensor_history")
public class SensorHistory implements Serializable {
	@Id
	@Column(name="device_id")
	private int deviceId;
	@Id
	@Column(name="type")
	private int type;
	@Column(name="sensor_index")
	private int sensorIndex;
	@Column(name="target")
	private BigDecimal target;
	@Column(name="value")
	private BigDecimal value;
	@Id
	@Column(name="created")
	private Date created;
}
