package com.aessense.agm.msr.interceptor;

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

import com.aessense.agm.msr.exception.UnauthorizedException;
import com.aessense.agm.msr.util.TokenFactory;

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
		
		String customerId = request.getParameter("customerID");
		if(StringUtils.isEmpty(customerId)) {
			throw new UnauthorizedException("Missing required request parameter: customerID");
		}
		
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			throw new UnauthorizedException("Missing required request parameter: token");
		}
		
		token = this.decodeToken(token);
		
		long timestamp = Long.parseLong(request.getParameter("timestamp"));
		byte[] digest = this.tokenFactory.generateRawToken(customerId, timestamp);
		String digestString = new String(digest);
		
		if(token.equals(digestString)) {
			return true;
		} else {
			throw new UnauthorizedException("Invalid token");			
		}
	}
	
	/**
	 * Decodes the token by first URL decoding then Base64 decoding.
	 * @param token
	 * @return
	 */
	private String decodeToken(String token) {
		String decodedToken = null;
		
		try {
			decodedToken = URLDecoder.decode(token, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Could not URL Decode token.");
		}
		
		decodedToken = new String(Base64.getDecoder().decode(decodedToken));
		return decodedToken;
	}
}
