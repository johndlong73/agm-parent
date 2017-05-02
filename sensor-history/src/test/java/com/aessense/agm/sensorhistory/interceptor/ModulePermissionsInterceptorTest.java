package com.aessense.agm.sensorhistory.interceptor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.aessense.agm.sensorhistory.exception.ForbiddenException;
import com.aessense.agm.sensorhistory.model.ModulePermission;
import com.aessense.agm.sensorhistory.model.ModuleType;
import com.aessense.agm.sensorhistory.persistence.Tenant;
import com.aessense.agm.sensorhistory.repository.agmcontrol.ModulePermissionRepository;

public class ModulePermissionsInterceptorTest {
	
	@Mock
	private ModulePermissionRepository repo;
	@InjectMocks
	private ModulePermissionsInterceptor interceptor;
	
	private ModulePermission enabled;
	
	private static final Long ENABLED_CUST=1001l;
	private static final Long DISABLED_CUST=1002l;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.setupPerms();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testPermissionEnabled() throws Exception {
		
		Tenant.setTenantId(ENABLED_CUST.toString());
		boolean result = this.interceptor.preHandle(request, response, null);
		assertTrue(result);
	}
	
	@Test(expected=ForbiddenException.class)
	public void testPermissionDisabled() throws Exception {

		Tenant.setTenantId(DISABLED_CUST.toString());
		boolean result = this.interceptor.preHandle(request, response, null);
	}

	private void setupPerms() {
		
		this.enabled = new ModulePermission();
		this.enabled.setEnable(true);
		List<ModulePermission> enabledList = new ArrayList<>();
		enabledList.add(this.enabled);
		
		when(this.repo.findByCustomerIdAndModuleIdAndEnable(ENABLED_CUST, 
				ModuleType.MULTI_SENSOR_REPORT.getValue(), true)).thenReturn(enabledList);
		
		when(this.repo.findByCustomerIdAndModuleIdAndEnable(DISABLED_CUST, 
				ModuleType.MULTI_SENSOR_REPORT.getValue(), true)).thenReturn(new ArrayList<ModulePermission>());
	}
}
