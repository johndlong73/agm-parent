package com.aessense.agm.sensorhistory.exception;

public class ServerException extends Exception {

	private static final long serialVersionUID = 2417403939595530764L;
	private final int code;
	private final String fields;
	
	public ServerException(int code, String fields, String message) {
		super(message);
		this.code = code;
		this.fields = fields;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getFields() {
		return this.fields;
	}
}
