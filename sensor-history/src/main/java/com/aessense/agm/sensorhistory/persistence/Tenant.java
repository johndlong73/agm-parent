package com.aessense.agm.sensorhistory.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tenant {
	
	private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

	private Tenant() {
		// Hiding default constructor
	}
	
	public static void setTenantId(String tenantId) {
		log.info("Setting current tenant: {}", tenantId);
		TENANT_ID.set(tenantId);
	}

	public static String getTenantId() {
		return TENANT_ID.get();
	}

	public static void clearCurrentTenant() {
		log.info("Clearing current tenant.");
		TENANT_ID.remove();
	}
}
