package com.aessense.agm.sensorhistory.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aessense.agm.sensorhistory.model.SensorHistory;
import com.aessense.agm.sensorhistory.model.SensorType;
import com.aessense.agm.sensorhistory.repository.agmreport.SensorHistoryRepository;
import com.aessense.agm.sensorhistory.rest.GetSensorHistoryRequest;
import com.aessense.agm.sensorhistory.rest.RestResponse;
import com.aessense.agm.sensorhistory.rest.SensorHistoryDto;

@Component
public class GetSensorHistoryAction {
	
	@Autowired
	private SensorHistoryRepository sensorHistoryRepository;
	
	public ResponseEntity doAction(GetSensorHistoryRequest input, HttpServletRequest request, HttpServletResponse response) {
		
		int deviceId = input.getDeviceId();
		Date start = input.getStartDate();
		Date end = input.getEndDate();
		
		List<SensorHistory> sensorHistoryList;
		
		if(input.getSensorTypes().isEmpty()) {
			sensorHistoryList = sensorHistoryRepository.findByDeviceIdBetweenStartAndEnd(input.getDeviceId(), input.getStartDate(), input.getEndDate());
		} else {
			sensorHistoryList = new ArrayList<>();
			for(SensorType t: input.getSensorTypes()) {
				sensorHistoryList.addAll(sensorHistoryRepository.findByDeviceIdAndTypeAndIndexBetweenStartAndEnd(deviceId, t.getId(), t.getIndex(), start, end));
			} 
		}
		
		RestResponse<SensorHistoryDto> restResponse = new RestResponse<>();
		List<SensorHistoryDto> dataList = new ArrayList<>();

		for(SensorHistory h : sensorHistoryList) {
			dataList.add(
				SensorHistoryDto.builder()
					.createdAt(h.getCreated())
					.deviceId(h.getDeviceId())
					.sensorIndex(h.getSensorIndex())
					.sensorType(SensorType.fromValues(h.getType(), h.getSensorIndex()).name())
					.target(h.getTarget())
					.value(h.getValue())
					.build());
		}
		
		restResponse.setData(dataList);
		restResponse.setCount(dataList.size());

		return ResponseEntity.status(HttpStatus.OK)
		.contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(restResponse);		
	}
}
