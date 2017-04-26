package com.aessense.agm.sensorhistory.repository.agmreport;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aessense.agm.sensorhistory.model.SensorHistory;
import com.aessense.agm.sensorhistory.util.AppConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorHistoryRepositoryTest {
	
	@Autowired
	SensorHistoryRepository repo;
	
	SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);

	@Test
	public void test() throws Exception {
		int[] devices = new int[1];
		devices[0] = 4;
		Date start = this.formatter.parse("2017-04-16T17:00:01.0");
		Date end = this.formatter.parse("2017-04-16T18:00:00.0");
		
		List<SensorHistory> data = this.repo.findByDeviceIdAndTypeBetweenStartAndEnd(206, devices, start, end);
		assertNotEquals(0, data.size());		
	}
}
