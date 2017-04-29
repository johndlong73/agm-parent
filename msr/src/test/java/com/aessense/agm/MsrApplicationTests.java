package com.aessense.agm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.aessense.agm.msr.config.AppConfig;
import com.aessense.agm.msr.util.TokenFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MsrApplicationTests {

	public static final String CUSTOMER_1001 = "1001";
	public static final String CUSTOMER_1002 = "1002";
	
	public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher FORBIDDEN = status().isForbidden();
	public static final ResultMatcher UNAUTHORIZED = status().isUnauthorized();
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private TokenFactory tokenFactory;
	
	@Autowired
    private AppConfig appConfig;
	
	@Test
	public void testGetReportSunnySide() throws Exception {
		
    	long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
		
		String url="/getReport?customerID=" + CUSTOMER_1001 + "&timestamp=" + timestamp + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(OK);

	}
	
	@Test
	public void testGetReportModulePermissionDisabled() throws Exception {
		
    	long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1002, timestamp);
		
		String url="/getReport?customerID=" + CUSTOMER_1002 + "&timestamp=" + timestamp + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(FORBIDDEN);
	}
	
	public void testTimestamp() throws Exception {
		
		long timestamp = new Date().getTime();
    	String token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
		
    	// Test sunnyside
		String url="/getReport?customerID=" + CUSTOMER_1001 + "&timestamp=" + timestamp + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(OK);
		
    	ResultMatcher expectedResult = null;
    	if(this.appConfig.isTimestampSecurityEnabled()) {
    		expectedResult = UNAUTHORIZED;
    	} else {
    		expectedResult = OK;
    	}
    	
    	// Expect 401 if timestamp is too old
    	timestamp = new Date().getTime() - 61000;
    	token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
    	url="/getReport?customerID=" + CUSTOMER_1001 + "&timestamp=" + timestamp + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(expectedResult);
		
		// Expect 401 if the timestamp is unparsable
		timestamp = new Date().getTime();
    	token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
    	url="/getReport?customerID=" + CUSTOMER_1001 + "&timestamp=" + "garbage" + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(expectedResult);
		
		// Expect 401 if the timestamp is omitted
		timestamp = new Date().getTime();
    	token = this.tokenFactory.generateUrlSafeToken(CUSTOMER_1001, timestamp);
    	url="/getReport?customerID=" + CUSTOMER_1001 + "&token=" + token;
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(expectedResult);
	}

}
