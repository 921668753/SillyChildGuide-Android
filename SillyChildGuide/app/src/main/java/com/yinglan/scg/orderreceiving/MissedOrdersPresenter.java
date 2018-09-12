package com.yinglan.scg.orderreceiving;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MissedOrdersPresenter implements MissedOrdersContract.Presenter {
    private MissedOrdersContract.View mView;

    public MissedOrdersPresenter(MissedOrdersContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGuideMissOrderPage(Context context, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageno", page);
        httpParams.put("pagesize", 5);
        RequestClient.getGuideOrderPage(context, httpParams, new ResponseListener<String>() {
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
