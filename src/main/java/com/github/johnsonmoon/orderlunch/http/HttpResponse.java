package com.github.johnsonmoon.orderlunch.http;

import java.util.HashMap;

/**
 * @Author: fanxx
 * @Date: 2019/7/26 下午5:56
 * @Description:
 */
public class HttpResponse {
    private int status;
    private Object data;
    private String msg;

    public HttpResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public HttpResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return status == 200;
    }

    public int getCode() {
        return status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
