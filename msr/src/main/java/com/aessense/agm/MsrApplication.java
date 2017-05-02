package com.aessense.agm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Main application class
 * @author John Long
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsrApplication extends SpringBootServletInitializer {

	/**
	 * Required to make war instead of jar.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MsrApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MsrApplication.class, args);
	}
}
