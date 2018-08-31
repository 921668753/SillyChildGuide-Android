package com.yinglan.scg.main;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ServicePresenter implements ServiceContract.Presenter {

    private ServiceContract.View mView;

    public ServicePresenter(ServiceContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAdvCat() {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("acid", "2");
//        RequestClient.getAdvCat(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.getSuccess(msg, 0);
//            }
//        });
    }


}
