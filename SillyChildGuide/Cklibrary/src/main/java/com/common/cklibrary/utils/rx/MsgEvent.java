package com.common.cklibrary.utils.rx;

/**
 * 自定义消息类
 * Created by Admin on 2017/11/8.
 */

public class MsgEvent<T> {

    private T data;
    private String msg;
    private int type;
    private String request;

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public MsgEvent(T data) {
        this.data = data;
    }

    public MsgEvent(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public MsgEvent(String request, String msg) {
        this.msg = msg;
        this.request = request;
    }

    public MsgEvent(int type, String request, String msg) {
        this.type = type;
        this.msg = msg;
        this.request = request;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() {
        return type;
    }

    public String getRequest() {
        return request;
    }

    public T getData() {
        return data;
    }

}
