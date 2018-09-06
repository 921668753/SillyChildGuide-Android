package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.search;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class AreaSearchListPresenter implements AreaSearchListContract.Presenter {

    private AreaSearchListContract.View mView;

    public AreaSearchListPresenter(AreaSearchListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCityListByName(String name) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("name", name);
        RequestClient.getCityListByName(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
