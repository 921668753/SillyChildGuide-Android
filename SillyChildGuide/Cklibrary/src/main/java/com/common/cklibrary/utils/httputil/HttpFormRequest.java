package com.common.cklibrary.utils.httputil;

import com.kymjs.rxvolley.client.FormRequest;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.RequestConfig;
import com.kymjs.rxvolley.toolbox.HttpParamsEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class HttpFormRequest extends FormRequest {

    public HttpFormRequest(RequestConfig config, HttpParams params, HttpCallback callback) {
        super(config, params, callback);
    }

    @Override
    protected void deliverResponse(ArrayList<HttpParamsEntry> headers, byte[] response) {
       // super.deliverResponse(headers, response);
        if (mCallback != null) {
            HashMap<String, String> map = new HashMap<>(headers.size());
            for (HttpParamsEntry entry : headers) {
                map.put(entry.k, entry.v);
            }
            mCallback.onSuccess(map, response);
        }
    }
}
