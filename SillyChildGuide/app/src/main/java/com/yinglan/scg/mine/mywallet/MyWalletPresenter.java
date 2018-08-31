package com.yinglan.scg.mine.mywallet;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class MyWalletPresenter implements MyWalletContract.Presenter {

    private MyWalletContract.View mView;

    public MyWalletPresenter(MyWalletContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyWallet() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMyWallet(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
