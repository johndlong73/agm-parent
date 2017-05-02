package com.aessense.agm.msr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Basic application configuration is centralized here.
 * @author John Long
 *
 */
@Data
@Component
public class AppConfig {

	// Enable or disabled timestamp security feature. Defaults to true 
	// if not overriden by system property.
	@Value("${timestamp.security.enabled:true}")
	private boolean timestampSecurityEnabled;
	
	// Enable or disable token security feature.  Defaults to true
	// if not overriden by system property.
	@Value("${token.security.enabled:true}")
	private boolean tokenSecurityEnabled;
	
}
