package com.common.cklibrary.utils.httputil;

/**
 * Created by ruitu on 2016/9/17.
 */

@SuppressWarnings("unchecked")
public interface ResponseListener<T> {
    @SuppressWarnings("unchecked")
    void onSuccess(T response);

    void onFailure(String msg);
}
