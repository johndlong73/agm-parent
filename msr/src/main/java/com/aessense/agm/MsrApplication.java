package com.aessense.agm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main application class
 * @author John Long
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsrApplication.class, args);
	}
}
