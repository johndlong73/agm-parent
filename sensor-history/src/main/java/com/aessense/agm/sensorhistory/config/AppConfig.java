package com.aessense.agm.sensorhistory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AppConfig {

	@Value("${timestamp.security.enabled:true}")
	private boolean timestampSecurityEnabled;
	@Value("${token.security.enabled:true}")
	private boolean tokenSecurityEnabled;
	
}
