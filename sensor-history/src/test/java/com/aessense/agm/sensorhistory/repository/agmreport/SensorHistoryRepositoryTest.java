package com.aessense.agm.sensorhistory.repository.agmreport;

import static org.junit.Assert.assertFalse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aessense.agm.sensorhistory.config.ConfigConstants;
import com.aessense.agm.sensorhistory.model.SensorHistory;
import com.aessense.agm.sensorhistory.model.SensorType;
import com.aessense.agm.sensorhistory.persistence.Tenant;
import com.aessense.agm.sensorhistory.util.DateFormatFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorHistoryRepositoryTest {
	
	@Autowired
	SensorHistoryRepository repo;
	
	@Autowired 
	private DateFormatFactory dateFormatFactory;
	

	@Test
	public void test() throws Exception {
		int[] devices = new int[1];
		devices[0] = 4;
		
		SimpleDateFormat formatter = this.dateFormatFactory.getDateFormat();
		Date start = formatter.parse("2017-04-17T17:00:00.0");
		Date end = formatter.parse("2017-04-17T18:00:00.0");
		
		Tenant.setTenantId("1001");
		List<SensorHistory> data = null;
		
//		List<SensorHistory> data = this.repo.findByDeviceIdAndTypeBetweenStartAndEnd(206, devices, start, end);
//		assertFalse(data.isEmpty());
		
		data = this.repo.findByDeviceIdAndTypeAndIndexBetweenStartAndEnd(206, SensorType.WEIGHT_4.getId(), SensorType.WEIGHT_4.getIndex(), start, end);
		assertFalse(data.isEmpty());
	}
}
