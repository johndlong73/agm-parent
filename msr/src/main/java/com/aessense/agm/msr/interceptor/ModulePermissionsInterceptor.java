package com.aessense.agm.msr.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.msr.exception.ForbiddenException;
import com.aessense.agm.msr.exception.UnauthorizedException;
import com.aessense.agm.msr.model.ModulePermission;
import com.aessense.agm.msr.model.ModuleType;
import com.aessense.agm.msr.repository.agmcontrol.ModulePermissionRepository;

@Component
public class ModulePermissionsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ModulePermissionRepository repo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String customerId = request.getParameter("customerID");
		
		if(StringUtils.isEmpty(customerId)) {
			throw new UnauthorizedException("Missing required request parameter: customerID");
		}
		
		List<ModulePermission> permissions = repo.findByCustomerIdAndModuleIdAndEnable(Long.parseLong(customerId),
				ModuleType.MULTI_SENSOR_REPORT.getValue(), true);
		
		if(permissions.isEmpty()) {
			throw new ForbiddenException("Customer does not have permissions to Multiple Sensor Report module.");
		}
		return true;
	}
}
