package com.aessense.agm.msr.exception;

/**
 * Exception to be thrown when we want the server to return 401.
 * @author John Long
 *
 */
public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = -2917776462136130570L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}
}
