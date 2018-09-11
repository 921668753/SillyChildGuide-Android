package com.yinglan.scg.orderreceiving;

import android.app.Activity;
import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.yinglan.scg.R;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class CharterDetailsPresenter implements CharterDetailsContract.Presenter {

    private CharterDetailsContract.View mView;

    public CharterDetailsPresenter(CharterDetailsContract.View view) {
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
    public void postGuideSubmitOrder(int model_id, String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("model_id", model_id);
        httpParams.put("order_number", order_number);
        RequestClient.postGuideSubmitOrder(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

}
