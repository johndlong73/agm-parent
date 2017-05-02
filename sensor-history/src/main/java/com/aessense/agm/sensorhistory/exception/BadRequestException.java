package com.aessense.agm.sensorhistory.exception;

public class BadRequestException extends ServerException {

	private static final long serialVersionUID = 956024937107667118L;

	public BadRequestException(int code, String fields, String message) {
		super(code, fields, message);
	}

}
