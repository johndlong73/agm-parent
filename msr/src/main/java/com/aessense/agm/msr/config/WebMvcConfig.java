package com.aessense.agm.msr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aessense.agm.msr.interceptor.ModulePermissionsInterceptor;
import com.aessense.agm.msr.interceptor.TimestampInterceptor;
import com.aessense.agm.msr.interceptor.TokenInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * Configure spring WebMvc.
 * @author John Long
 *
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private TimestampInterceptor timestampInterceptor;
	
	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	@Autowired
	private ModulePermissionsInterceptor permissionInterceptor;
	
	@Autowired
	private AppConfig config;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		if(this.config.isTimestampSecurityEnabled()) {
			registry.addInterceptor(this.timestampInterceptor);
		}
		
		if(this.config.isTokenSecurityEnabled()) {
			registry.addInterceptor(this.tokenInterceptor);
		}
		
		registry.addInterceptor(this.permissionInterceptor);
	}
}
