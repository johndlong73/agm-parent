/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aessense.agm.sensorhistory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.aessense.agm.sensorhistory.config.AppConfig;
import com.aessense.agm.sensorhistory.util.DateFormatFactory;
import com.aessense.agm.sensorhistory.util.TokenFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SensorHistoryTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private DateFormatFactory dateFormatFactory;
    
    @Autowired
    private TokenFactory tokenFactory;
    
    @Autowired
    private AppConfig appConfig;
    
    public static final String CUSTOMER_1001 = "1001";
    public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher FORBIDDEN = status().isForbidden();
	public static final ResultMatcher UNAUTHORIZED = status().isUnauthorized();

    @Test
    public void testGetSuccess() throws Exception {
    	
    	long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);

    	String url = "/v1.0/" + CUSTOMER_1001 + "/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());  
    }

    @Test
    public void testGetMissingDates() throws Exception {
    	
    	long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
    	        
		String urlNoStartDate = "/v1.0/1001/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_0,TOTAL_CONDUCTIVITY&endDate=2017-04-19T16:59:55.0";
        this.mockMvc.perform(get(urlNoStartDate)).andDo(print())
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
        	.andExpect(jsonPath("$.fields").value("startDate"))
        	.andExpect(jsonPath("$.message").isNotEmpty());
        
		String urlNoEndDate = "/v1.0/1001/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_0,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:01.0";
        this.mockMvc.perform(get(urlNoEndDate)).andDo(print())
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
        	.andExpect(jsonPath("$.fields").value("endDate"))
        	.andExpect(jsonPath("$.message").isNotEmpty());
    } 
    
    @Test
    public void testTimestamp() throws Exception {
    	
    	long timestamp = new Date().getTime() - 61000;
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);

    	if(this.appConfig.isTimestampSecurityEnabled()) {
	    	// Expect 401 if the timestamp is too old.
	    	String url = "/v1.0/1001/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
	        this.mockMvc.perform(get(url))
	        	.andDo(print())
	        	.andExpect(status().isUnauthorized())
	        	.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()))
	        	.andExpect(jsonPath("$.fields").value("timestamp"))
	        	.andExpect(jsonPath("$.message").isNotEmpty());
	        
	        // Expect 200 if the timestamp is less than 5 seconds into the future
	        timestamp = new Date().getTime() + 4500;
	        token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
	        url = "/v1.0/1001/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
	        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
	        
	        // Expect 401 if the timestamp is too far into the future (more than about 5 seconds).
	        timestamp = new Date().getTime() + 6000;
	        token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
	        url = "/v1.0/1001/sensorHistory?timestamp=" + timestamp + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
	        this.mockMvc.perform(get(url)).andDo(print())
	        	.andExpect(status().isUnauthorized())
	        	.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()))
	        	.andExpect(jsonPath("$.fields").value("timestamp"))
	        	.andExpect(jsonPath("$.message").isNotEmpty());
	        
	        // Expect 401 if the timestamp is unparsable
	        url = "/v1.0/1001/sensorHistory?timestamp=" + "garbage" + "&token=" + token + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
	        this.mockMvc.perform(get(url)).andDo(print())
	        	.andExpect(status().isUnauthorized())
	        	.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()))
	        	.andExpect(jsonPath("$.fields").value("timestamp"))
	        	.andExpect(jsonPath("$.message").isNotEmpty());
	        
	        // Expect 401 if the timestamp is omitted
	        url = "/v1.0/1001/sensorHistory?deviceId=206" + "&token=" + token + "&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
	        this.mockMvc.perform(get(url)).andDo(print())
	        	.andExpect(status().isUnauthorized())
	        	.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()))
	        	.andExpect(jsonPath("$.fields").value("timestamp"))
	        	.andExpect(jsonPath("$.message").isNotEmpty());
    	}
    }    

    @Test
    public void testToken() throws Exception {
    	
    	long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);

    	String url = "/v1.0/" + CUSTOMER_1001 + "/sensorHistory?timestamp=" + timestamp + "&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-17T17:00:00.000&endDate=2017-04-17T18:00:00.000";
    	
    	if(this.appConfig.isTokenSecurityEnabled()) {
    		this.mockMvc.perform(get(url)).andDo(print())
	    		.andExpect(status().isUnauthorized())
	        	.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()))
	        	.andExpect(jsonPath("$.fields").value("token"))
	        	.andExpect(jsonPath("$.message").isNotEmpty());
    	} else {
    		this.mockMvc.perform(get(url)).andDo(print())
        		.andExpect(status().isOk());    		
    	}
    }    
}
