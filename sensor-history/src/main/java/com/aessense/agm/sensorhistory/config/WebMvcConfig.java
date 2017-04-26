package com.aessense.agm.sensorhistory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aessense.agm.sensorhistory.interceptor.TenantInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

 @Override
 public void addInterceptors(InterceptorRegistry registry) {
  registry.addInterceptor(new TenantInterceptor());
 }
}
