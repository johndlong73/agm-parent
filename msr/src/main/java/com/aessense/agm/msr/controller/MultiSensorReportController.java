package com.aessense.agm.msr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aessense.agm.msr.action.GetMultiSensorReportAction;

@Controller
public class MultiSensorReportController {

	@Autowired
	private GetMultiSensorReportAction getMultiSensorReportAction;
	
	@RequestMapping("/getReport")
	public String getMultiSensorReport(Model model) {
		return this.getMultiSensorReportAction.doAction();
	}
}
