package com.best.msg;

public class PageResponse<T> extends ListResponse<T> {
	protected long sum;

	public long getSum() {
		return this.sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}
}
