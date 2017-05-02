package com.aessense.agm.sensorhistory.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.sensorhistory.exception.ServerException;
import com.aessense.agm.sensorhistory.exception.UnauthorizedException;
import com.aessense.agm.sensorhistory.persistence.Tenant;
import com.aessense.agm.sensorhistory.util.TokenFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Intercepts and validates the token request parameter.  Makes the application
 * return 401 if token is invalid or missing.
 * 
 * @author John Long
 */
@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TokenFactory tokenFactory;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// Get the token request parameter.
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "token", "Missing token");
		}
		
		// Decode the token
		token = this.decodeToken(token);
		
		// Generate a raw token to compare against incoming token
		long timestamp = Long.parseLong(request.getParameter("timestamp"));
		String customerId = Tenant.getTenantId();
		byte[] digest = this.tokenFactory.generateRawToken(customerId, timestamp);
		String digestString = new String(digest);
		
		// Incoming token is valid if it matches raw token generated in this code.
		if(token.equals(digestString)) {
			return true;
		} else {
			throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "token", "Invalid token");			
		}
	}
	
	/**
	 * Decodes the token by first URL decoding then Base64 decoding.
	 * @param token
	 * @return
	 * @throws ServerException 
	 */
	private String decodeToken(String token) throws ServerException {
		String decodedToken = null;
		
		try {
			// Remove URL encoding.
			decodedToken = URLDecoder.decode(token, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "token", "Invalid token");
		}
		
		// Remove Base64 encoding
		decodedToken = new String(Base64.getDecoder().decode(decodedToken));
		return decodedToken;
	}
}
