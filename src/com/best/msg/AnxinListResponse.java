package com.best.msg;

import java.util.List;

public class AnxinListResponse<T> {
	protected String error;
	protected List<T> data;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	
}
