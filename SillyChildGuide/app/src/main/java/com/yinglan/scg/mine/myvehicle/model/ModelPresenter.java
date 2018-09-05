package com.yinglan.scg.mine.myvehicle.model;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

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
    public void getModelBrandList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getModelBrandList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getModelNameList(int brand_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("brand_id", brand_id);
        RequestClient.getModelNameList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
