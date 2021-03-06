package com.yinglan.scg.service.dialog;

import android.content.Context;

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
    public void getGuideOrderStroke(Context context, String time) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("time", time);
        RequestClient.getGuideOrderStroke(context, httpParams, new ResponseListener<String>() {
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
