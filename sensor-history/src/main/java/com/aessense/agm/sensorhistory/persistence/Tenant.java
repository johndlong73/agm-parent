package com.aessense.agm.sensorhistory.persistence;

public class Tenant {
	
	private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

	public static void setTenantId(String tenantId) {
		TENANT_ID.set(tenantId);
	}

	public static String getTenantId() {
		return TENANT_ID.get();
	}

	public static void clearCurrentTenant() {
		TENANT_ID.remove();
	}
}
