package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/2/11.
 */

public class MyVehiclePresenter implements MyVehicleContract.Presenter {

    private MyVehicleContract.View mView;

    public MyVehiclePresenter(MyVehicleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyVehicleList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getModelList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
