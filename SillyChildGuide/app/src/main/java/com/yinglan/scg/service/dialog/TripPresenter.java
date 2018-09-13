package com.yinglan.scg.service.dialog;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class TripPresenter implements TripContract.Presenter {

    private TripContract.View mView;

    public TripPresenter(TripContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCancelReasonList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCancelReasonList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postGuideSubmitOrder(String order_number, String cancel_reason) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
        httpParams.put("order_state", 2);
        httpParams.put("cancel_reason", cancel_reason);
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
