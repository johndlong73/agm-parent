package com.aessense.agm.msr.action;

import org.springframework.stereotype.Component;

/**
 * Command class that implements the business logic to return the getReport page
 * @author John Long
 *
 */
@Component
public class GetMultiSensorReportAction {

	/**
	 * Perform the business logic and return the name of the view to display.
	 */
	public String doAction() {
		// This method is a stub. Will need to add Model to argument list, do
		// business logic and return view name.  Right now just returns view
		// name.
		return "getReport";
	}
}
