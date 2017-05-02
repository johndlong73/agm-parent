package com.aessense.agm.sensorhistory.persistence;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Custom datasource that enables the multitenant feature.  
 * 
 * This datasource contains a map of datasources.  It tries to find a match
 * based on customer id.  Returns the default datasource if no match is found.
 * The default datasource must not point to any customer's datasource or the
 * application may leak that customers data.
 * 
 * @author John Long
 *
 */
public class CustomRoutingDataSource extends AbstractRoutingDataSource {
	
	/**
	 * Determine the lookup key that will be used to lookup the customer's
	 * datasource.
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String key;
		// Get the customerID.
		String customerId = Tenant.getTenantId();
		if(null == customerId) {
			key = null;
		} else {
			// return the datasource name
			key= "customerDataSource" + Tenant.getTenantId();
		}
		return key;
	}
}
