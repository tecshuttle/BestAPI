package com.best.msg;

public class GenResponse<T> extends BaseResponse {
	protected T response;

	public T getResponse() {
		return (T) this.response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
}
