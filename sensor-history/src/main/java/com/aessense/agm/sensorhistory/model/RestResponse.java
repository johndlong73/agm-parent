package com.aessense.agm.sensorhistory.model;

import java.util.List;

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
