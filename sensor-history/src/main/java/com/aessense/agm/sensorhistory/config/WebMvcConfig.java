package com.aessense.agm.sensorhistory.config;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aessense.agm.sensorhistory.interceptor.ModulePermissionsInterceptor;
import com.aessense.agm.sensorhistory.interceptor.TenantInterceptor;
import com.aessense.agm.sensorhistory.interceptor.TimestampInterceptor;
import com.aessense.agm.sensorhistory.interceptor.TokenInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * Configure spring WebMvc.
 * @author John Long
 *
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimestampInterceptor timestampInterceptor;
	
	@Autowired
	private TenantInterceptor tenantInterceptor;
	
	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	@Autowired
	private ModulePermissionsInterceptor permissionInterceptor;
	
	@Autowired
	private AppConfig config;

	/**
	 * Wire up interceptors which perform security checks.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(this.config.isTimestampSecurityEnabled()) {
			registry.addInterceptor(this.timestampInterceptor);
		}
		
		registry.addInterceptor(this.tenantInterceptor);
		
		if(this.config.isTokenSecurityEnabled()) {
			registry.addInterceptor(this.tokenInterceptor);
		}
		
		registry.addInterceptor(this.permissionInterceptor);
	}
}
