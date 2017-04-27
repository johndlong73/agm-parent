package com.aessense.agm.sensorhistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aessense.agm.sensorhistory.interceptor.TenantInterceptor;
import com.aessense.agm.sensorhistory.interceptor.TimestampInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private TimestampInterceptor timestampInterceptor;
	
	@Autowired
	private TenantInterceptor tenantInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.timestampInterceptor);
		registry.addInterceptor(this.tenantInterceptor);
	}
}
