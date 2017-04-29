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

@Component
public class ModulePermissionsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ModulePermissionRepository repo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String customerId = Tenant.getTenantId();
		List<ModulePermission> permissions = repo.findByCustomerIdAndModuleIdAndEnable(Long.parseLong(customerId),
				ModuleType.MULTI_SENSOR_REPORT.getValue(), true);
		
		if(permissions.isEmpty()) {
			response.sendError(HttpStatus.FORBIDDEN.value());
			return false;
		}
		return true;
	}
}
