package com.yinglan.scg.mine.myorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class CharterOrderDetailsPresenter implements CharterOrderDetailsContract.Presenter {
    private CharterOrderDetailsContract.View mView;

    public CharterOrderDetailsPresenter(CharterOrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取订单的详细信息
     */
    @Override
    public void getTravelOrderDetails(String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
        RequestClient.getTravelOrderDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
