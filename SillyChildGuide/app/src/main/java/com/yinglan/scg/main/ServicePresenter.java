package com.yinglan.scg.main;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class ServicePresenter implements ServiceContract.Presenter {

    private ServiceContract.View mView;

    public ServicePresenter(ServiceContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyOrderPage(Context context, int status, int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("status", status);
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 5);
        RequestClient.getMyOrderPage(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

}
