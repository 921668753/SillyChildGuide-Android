package com.yinglan.scg.message.systemmessage;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by Administrator on 2017/2/17.
 */

public class SystemMessageListPresenter implements SystemMessageListContract.Presenter {


    private SystemMessageListContract.View mView;

    public SystemMessageListPresenter(SystemMessageListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSystemMessageList(String title, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("title", title);
        httpParams.put("page", page);
        RequestClient.getSystemMessageList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
