package com.yinglan.scg.loginregister;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2017/8/24.
 */

public class SelectCountryPresenter implements SelectCountryContract.Presenter {

    private SelectCountryContract.View mView;

    public SelectCountryPresenter(SelectCountryContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCountryNumber() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCountryNumber(httpParams, new ResponseListener<String>() {
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
