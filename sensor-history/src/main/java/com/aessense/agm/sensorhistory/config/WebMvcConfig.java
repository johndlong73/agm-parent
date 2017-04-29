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

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
	
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

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
        // can be set runtime before Spring instantiates any beans
        // TimeZone.setDefault(TimeZone.getTimeZone("GMT+00:00"))
		log.info("Setting default timezone to UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));    

        // cannot override encoding in Spring at runtime as some strings have already been read
        // however, we can assert and ensure right values are loaded here

        // verify system property is set
        Assert.isTrue(ConfigConstants.FILE_ENCODING.equals(System.getProperty("file.encoding")), "Error: file.encoding property must be " + ConfigConstants.FILE_ENCODING);;

        // and actually verify it is being used
        Charset charset = Charset.defaultCharset();
        Assert.isTrue(charset.equals(Charset.forName(ConfigConstants.FILE_ENCODING)),"");
		
	}
}
