package com.aessense.agm.sensorhistory.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aessense.agm.sensorhistory.util.DateFormatFactory;

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
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;			
		}
		
		Date timestamp = null;
		
		// If the timestamp won't parse then return 401
		try {
			timestamp = dateFormatter.getDateFormat().parse(timestampParam);
		} catch(Exception e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		
		Date now = new Date();
		long delta = now.getTime() - timestamp.getTime();
		
		// If the timestamp is older than one minute then return 401
		if(delta > ONE_MINUTE) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;
		} else {
			return true;
		}
	}
}
