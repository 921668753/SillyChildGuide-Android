package com.common.cklibrary.entity;

import java.io.Serializable;

/**
 * Created by ruitu on 2016/11/17.
 */

public class BaseResult<T> implements Serializable {

    /**
     * result : 1
     * message : 发送成功
     * data : null
     */

    private int result;
    private String message;
    private T data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
