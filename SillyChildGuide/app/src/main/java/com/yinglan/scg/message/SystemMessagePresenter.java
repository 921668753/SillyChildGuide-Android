package com.yinglan.scg.message;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SystemMessagePresenter implements SystemMessageContract.Presenter {
    private SystemMessageContract.View mView;

    public SystemMessagePresenter(SystemMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getSystem(Context context, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getSystemMessage(context, httpParams, new ResponseListener<String>() {

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
