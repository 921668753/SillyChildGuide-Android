package com.yinglan.scg.service.dialog;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class EndTheOrderPresenter implements EndTheOrderContract.Presenter {

    private EndTheOrderContract.View mView;

    public EndTheOrderPresenter(EndTheOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postEndTheOrder(Context context, String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
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
}
