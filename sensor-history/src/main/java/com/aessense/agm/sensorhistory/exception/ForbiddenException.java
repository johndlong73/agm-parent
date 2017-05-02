package com.aessense.agm.sensorhistory.exception;

/**
 * Exception to be thrown when we want the server to return 403.
 * @author John Long
 *
 */
public class ForbiddenException extends ServerException {

	private static final long serialVersionUID = 1932526610244484908L;
	
	public ForbiddenException(int code, String fields, String message) {
		super(code, fields, message);
	}
}
