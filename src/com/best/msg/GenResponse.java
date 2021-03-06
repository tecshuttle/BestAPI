package com.best.msg;

public class GenResponse<T> extends BaseResponse {
    protected T response;
    protected String success = "true";

    public T getResponse() {
        return (T) this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
