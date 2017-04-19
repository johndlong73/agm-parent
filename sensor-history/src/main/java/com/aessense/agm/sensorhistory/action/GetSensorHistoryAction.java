package com.aessense.agm.sensorhistory.action;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aessense.agm.sensorhistory.model.RestResponse;
import com.aessense.agm.sensorhistory.model.SensorData;

@Component
public class GetSensorHistoryAction {

	public RestResponse<SensorData> doAction() {

		RestResponse<SensorData> response = new RestResponse<>();
		List<SensorData> dataList = new ArrayList<>();

		dataList.addAll(this.generateMockSensorData(1l, 1l, 1l, 5));
		dataList.addAll(this.generateMockSensorData(2l, 2l, 2l, 5));
		dataList.addAll(this.generateMockSensorData(3l, 3l, 3l, 5));
		
		response.setData(dataList);
		response.setCount(dataList.size());

		return response;
	}
	
	private List<SensorData> generateMockSensorData(long deviceId, long sensorIndex, long sensorType, int count) {
		List<SensorData> dataList = new ArrayList<>();
		
		
		
		for(int i = 0; i < count; i++) {
			SensorData d = SensorData.builder().createdAt(new Date()).deviceId(deviceId).sensorIndex(sensorIndex).sensorType(sensorType)
					.target(BigDecimal.valueOf(2.0)).value(BigDecimal.valueOf(1.0)).build();
			dataList.add(d);
		}
		return dataList;
	}
}
