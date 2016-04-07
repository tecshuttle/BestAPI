package com.best.msg;

public class ExtResponse<T>  {
    protected String result = "";
    protected String success = "true";

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
