package com.aessense.agm.sensorhistory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="agm_module_permissions", schema="agmadmin")
public class ModulePermission implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="customer_id")
	private long customerId;
	@Column(name="agm_module_id")
	private long moduleId;
	@Column(name="enable")
	private Boolean enable;

}
