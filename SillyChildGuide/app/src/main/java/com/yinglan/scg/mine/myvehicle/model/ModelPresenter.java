package com.yinglan.scg.mine.myvehicle.model;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ModelPresenter implements ModelContract.Presenter {
    private ModelContract.View mView;

    public ModelPresenter(ModelContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAirportCountryList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAirportCountryList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void getAirportByCountryId(int country_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("country_id", country_id);
//        RequestClient.getAirportByCountryId(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }
}
