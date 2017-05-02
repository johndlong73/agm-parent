package com.aessense.agm.sensorhistory.exception;

/**
 * Exception to be thrown when we want the server to return 401.
 * @author John Long
 *
 */
public class UnauthorizedException extends ServerException {
	
	private static final long serialVersionUID = -2917776462136130570L;

	public UnauthorizedException(int code, String fields, String message) {
		super(code, fields, message);
	}
}
