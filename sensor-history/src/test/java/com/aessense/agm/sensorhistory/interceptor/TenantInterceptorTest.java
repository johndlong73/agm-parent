package com.aessense.agm.sensorhistory.interceptor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.aessense.agm.sensorhistory.persistence.Tenant;

public class TenantInterceptorTest {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private TenantInterceptor interceptor;
	
	public static final String TENANT_ID = "1001";
	
	@Before
	public void setup() {
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
		
		this.request.setPathInfo("/v1.0/" + TENANT_ID + "/sensorHistory");
		
		this.interceptor = new TenantInterceptor();
	}

	//@Test
	public void test() throws Exception {
		assertTrue(this.interceptor.preHandle(request, response, null));
		assertEquals(TENANT_ID, Tenant.getTenantId());
	}

}
