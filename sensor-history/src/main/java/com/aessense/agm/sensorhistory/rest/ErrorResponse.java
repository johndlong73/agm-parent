package com.aessense.agm.sensorhistory.rest;

import java.io.Serializable;

import com.aessense.agm.sensorhistory.exception.ServerException;

import lombok.Data;

/**
 * Rest response for errors.
 * @author John Long
 *
 */
@Data
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 3243249510986464365L;
	private int code;
	private String message;
	private String fields;
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(ServerException e) {
		this.code = e.getCode();
		this.message = e.getMessage();
		this.fields = e.getFields();
	}
}
