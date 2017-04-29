package com.aessense.agm.sensorhistory.controller;

import static com.aessense.agm.sensorhistory.config.ConfigConstants.SENSOR_HISTORY_URL;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aessense.agm.sensorhistory.action.GetSensorHistoryAction;
import com.aessense.agm.sensorhistory.config.ConfigConstants;
import com.aessense.agm.sensorhistory.rest.GetSensorHistoryRequest;
import com.aessense.agm.sensorhistory.util.SensorTypeHelper;

@RestController
public class SensorHistoryController {

	@Autowired
	private GetSensorHistoryAction getSensorHistoryAction;

	SimpleDateFormat formatter = new SimpleDateFormat(ConfigConstants.DATE_FORMAT);

	@RequestMapping(SENSOR_HISTORY_URL)
	public ResponseEntity getSensorHistory(@PathVariable long customerId, int deviceId, String[] sensorTypes,
			@RequestParam(required=true) @DateTimeFormat(pattern=ConfigConstants.DATE_FORMAT) Date startDate, 
			@RequestParam(required=true) @DateTimeFormat(pattern=ConfigConstants.DATE_FORMAT) Date endDate, 
			HttpServletRequest request, HttpServletResponse response) {


		GetSensorHistoryRequest restRequest = GetSensorHistoryRequest.builder()
				.customerId(customerId)
				.deviceId(deviceId)
				.sensorTypes(SensorTypeHelper.stringArrayToEnumList(sensorTypes))
				.startDate(startDate)
				.endDate(endDate)
				.build();


		return this.getSensorHistoryAction.doAction(restRequest, request, response);
		
	}
}
