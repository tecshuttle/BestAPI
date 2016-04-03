package com.best.msg;

import java.util.List;

public class ListResponse<T> extends BaseResponse {
	protected List<T> response;

	public List<T> getResponse() {
		return this.response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	}
}
