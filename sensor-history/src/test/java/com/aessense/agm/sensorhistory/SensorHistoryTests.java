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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aessense.agm.sensorhistory.util.DateFormatFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SensorHistoryTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private DateFormatFactory dateFormatFactory;

    @Test
    public void testGet() throws Exception {
    	
//    	Date now = new Date();
//    	String nowString = URLEncoder.encode(this.dateFormatFactory.getDateFormat().format(now), "UTF-8");

    	String url = "/v1.0/1001/sensorHistory?deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-16T17:00:00.000&endDate=2017-04-16T21:00:00.000";
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());

        url = "/v1.0/1002/sensorHistory?deviceId=206&sensorTypes=WEIGHT_0,TOTAL_CONDUCTIVITY&startDate=2017-04-16T17:00:00.000&endDate=2017-04-16T18:00:00.000";
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
        
		String urlNoStartDate = "/v1.0/1001/sensorHistory?deviceId=206&sensorTypes=WEIGHT_0,TOTAL_CONDUCTIVITY&endDate=2017-04-19T16:59:55.0";
        this.mockMvc.perform(get(urlNoStartDate)).andDo(print()).andExpect(status().isBadRequest());
        

		String urlNoEndDate = "/v1.0/1001/sensorHistory?deviceId=206&sensorTypes=WEIGHT_0,TOTAL_CONDUCTIVITY&startDate=2017-04-16T17:00:01.0";
        this.mockMvc.perform(get(urlNoEndDate)).andDo(print()).andExpect(status().isBadRequest());
    }

//    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }

}
