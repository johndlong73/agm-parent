package com.aessense.agm.msr.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.msr.exception.UnauthorizedException;
import com.aessense.agm.msr.util.DateFormatFactory;

/**
 * Replay prevention mechanism.  Validates that the timestamp sent in the timestamp
 * query parameter is less than one minute old otherwise makes the app return
 * UNAUTHORIZED
 * 
 * @author John Long
 *
 */
@Component
public class TimestampInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private DateFormatFactory dateFormatter;
	
	private static final long ONE_MINUTE = 60000;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String timestampParam = request.getParameter("timestamp");
		
		// If the timestamp query param is missing or blank then return 401
		if(StringUtils.isEmpty(timestampParam)) {
			throw new UnauthorizedException("Missing required request parameter: timestamp");			
		}
		
		long timestamp;

		// If the timestamp won't parse then return 401
		try {
			timestamp = Long.parseLong(timestampParam);
		} catch(Exception e) {
			throw new UnauthorizedException("Invalid value for request parameter: timestamp");
		}
		
		long now = new Date().getTime();
		long delta = now - timestamp;
		
		// If the timestamp is older than one minute then return 401
		if(delta > ONE_MINUTE || delta < -5000 ) {
			throw new UnauthorizedException("Timestamp is out of range");
		} else {
			return true;
		}
	}
}
