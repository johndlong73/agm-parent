package com.aessense.agm.sensorhistory.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

public class TenantTest {
	
	public static final String TENANT_ID = "1001";

	@Test
	public void test() {
		Tenant.setTenantId(TENANT_ID);
		assertEquals(Tenant.getTenantId(), TENANT_ID);
	}
}
