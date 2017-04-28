package com.aessense.agm.sensorhistory.interceptor;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.sensorhistory.persistence.Tenant;

/**
 * Intercepts and validates the token request parameter.  Makes the application
 * return 401 if token is invalid or missing.
 * 
 * @author John Long
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		
		String timestamp = request.getParameter("timestamp");
		
		String customerId = Tenant.getTenantId();
		
		String hashSource = "AGM"+ customerId + timestamp;
		
		MessageDigest digester = MessageDigest.getInstance("MD5");
		
		byte[] digest = digester.digest(hashSource.getBytes());
		
		String digestString = new String(digest);
		
		if(token.equals(digestString)) {
			return true;
		} else {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;			
		}
	}
}
