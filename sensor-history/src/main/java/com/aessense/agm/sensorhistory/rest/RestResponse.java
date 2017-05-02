package com.aessense.agm.sensorhistory.rest;

import java.util.List;

/**
 * Pojo for encapsulating rest response envelope.
 * @author John Long
 *
 */
public class RestResponse<T> {
	private long count;
	private List<T> data;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
