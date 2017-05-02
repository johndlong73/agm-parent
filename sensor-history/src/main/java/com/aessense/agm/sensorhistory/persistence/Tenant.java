package com.aessense.agm.sensorhistory.persistence;

import lombok.extern.slf4j.Slf4j;

/**
 * Stores the tenant (customer) information in thread local storage so 
 * that it can be looked up anywhere in the application in a safe way
 * that doesn't leak.
 * 
 * @author John Long
 *
 */
@Slf4j
public class Tenant {
	
	// Use thread local storage because a user's request is bound to a thread 
	// and this information may be needed by classes that don't have access
	// to the HttpRequest or the spring context.
	private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

	private Tenant() {
		// Hiding default constructor
	}
	
	/**
	 * Store the tenant id.
	 * 
	 */
	public static void setTenantId(String tenantId) {
		log.info("Setting current tenant: {}", tenantId);
		TENANT_ID.set(tenantId);
	}

	/**
	 * Return the tenant id.
	 * 
	 */
	public static String getTenantId() {
		return TENANT_ID.get();
	}

	/**
	 * Clear the tenant id.
	 */
	public static void clearCurrentTenant() {
		log.info("Clearing current tenant.");
		TENANT_ID.remove();
	}
}
