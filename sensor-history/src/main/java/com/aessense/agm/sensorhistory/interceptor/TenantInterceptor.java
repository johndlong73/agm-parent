package com.aessense.agm.sensorhistory.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.sensorhistory.persistence.Tenant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {
	
	private static final String CUST_ID = "customerId";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if( pathVars.containsKey(CUST_ID)) {
			String customerId = (String) pathVars.get(CUST_ID);
			log.info("Setting tentant to: {}", customerId);
			Tenant.setTenantId(customerId); 
		}
		return true;
	}
}
