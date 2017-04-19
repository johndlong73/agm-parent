package com.aessense.agm.sensorhistory.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aessense.agm.sensorhistory.action.GetSensorHistoryAction;
import com.aessense.agm.sensorhistory.model.RestResponse;
import com.aessense.agm.sensorhistory.model.SensorData;

@RestController
public class SensorHistoryController {
	
	@Autowired
	private GetSensorHistoryAction getSensorHistoryAction;

	@RequestMapping("/v1.0/{customerId}/sensorHistory")
	public RestResponse<SensorData> getSensorHistory(@PathVariable long customerId, long deviceId, Date startDate, Date endDate) {
		return this.getSensorHistoryAction.doAction();
	}
}
