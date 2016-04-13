package com.best.msg;

import java.util.LinkedList;
import java.util.List;

public class ExtListResponse<T> extends BaseResponse {
    protected List<T> response;
    protected int total = 0;

    public List<T> getResponse() {
        return this.response;
    }

    public void setResponse(List<T> response, int start, int limit) {
        this.setTotal(response.size());

        List<T> list = new LinkedList<T>();

        int end = start + limit;

        for (int i = start; i < end; i++) {
            if ((i + 1) > this.total) {
                break;
            }

            list.add(response.get(i));
        }

        this.response = list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

//end file
