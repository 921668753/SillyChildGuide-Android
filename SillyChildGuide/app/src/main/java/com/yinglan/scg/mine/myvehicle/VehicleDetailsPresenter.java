package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/2/11.
 */

public class VehicleDetailsPresenter implements VehicleDetailsContract.Presenter {

    private VehicleDetailsContract.View mView;

    public VehicleDetailsPresenter(VehicleDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getModelDetail(int model_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("model_id", model_id);
        RequestClient.getModelDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
