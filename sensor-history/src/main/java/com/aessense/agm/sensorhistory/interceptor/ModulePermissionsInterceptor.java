package com.aessense.agm.sensorhistory.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.sensorhistory.model.ModulePermission;
import com.aessense.agm.sensorhistory.model.ModuleType;
import com.aessense.agm.sensorhistory.persistence.Tenant;
import com.aessense.agm.sensorhistory.repository.agmcontrol.ModulePermissionRepository;

/**
 * Verifies that the customer has permission to get sensory history.  Returns
 * HTTP forbidden if not permitted.
 * @author John Long
 *
 */
@Component
public class ModulePermissionsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ModulePermissionRepository repo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// Get the customer id
		String customerId = Tenant.getTenantId();
		
		// Look up the permission.  Will get an empty list if not permitted
		List<ModulePermission> permissions = repo.findByCustomerIdAndModuleIdAndEnable(Long.parseLong(customerId),
				ModuleType.MULTI_SENSOR_REPORT.getValue(), true);
		
		// If list is empty then return forbidden
		if(permissions.isEmpty()) {
			response.sendError(HttpStatus.FORBIDDEN.value());
			return false;
		}
		
		// Customer has permission, return true.
		return true;
	}
}
