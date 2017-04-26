package com.aessense.agm.sensorhistory.persistence;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class CustomRoutingDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		String key;
		String customerId = Tenant.getTenantId();
		if(null == customerId) {
			key = null;
		} else {
			key= "customerDataSource" + Tenant.getTenantId();
		}
		return key;
	}
}
