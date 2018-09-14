package com.yinglan.scg.service;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2017/8/24.
 */

public class TravelCalendarPresenter implements TravelCalendarContract.Presenter {

    private TravelCalendarContract.View mView;

    public TravelCalendarPresenter(TravelCalendarContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getDateList(Context context) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getDateList(context, httpParams, new ResponseListener<String>() {
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
