package com.aessense.agm.msr.exception;

/**
 * Exception to be thrown when we want the server to return 403.
 * @author John Long
 *
 */
public class ForbiddenException extends Exception {

	public ForbiddenException() {
		super();
	}

	public ForbiddenException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1932526610244484908L;

}
