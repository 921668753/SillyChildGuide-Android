package com.common.cklibrary.utils.httputil;

@SuppressWarnings("unchecked")
public interface ResponseProgressbarListener<T> extends ResponseListener<T> {

    void onProgress(String progress);

}
