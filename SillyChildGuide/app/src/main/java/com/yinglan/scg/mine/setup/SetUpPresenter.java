package com.yinglan.scg.mine.setup;

import android.app.Activity;
import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.yinglan.scg.R;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class SetUpPresenter implements SetUpContract.Presenter {

    private SetUpContract.View mView;

    public SetUpPresenter(SetUpContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUpdateApp() {

    }

    /**
     * @param updateAppUrl 下载app
     */
    @Override
    public void downloadApp(String updateAppUrl) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download));
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                String size = MathUtil.keepZero(((double) transferredBytes) * 100 / totalSize) + "%";
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download) + size);
            }
        };
        RequestClient.downloadApp(updateAppUrl, progressListener, new ResponseListener<String>() {
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


    /**
     * 退出app登录
     */
    @Override
    public void logOutAPP(Activity activity) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.postLogout(activity, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                logOutApp(activity);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    private void logOutApp(Activity activity) {
        /*//这些数据清除操作之前一直是在login界面,因为app的数据库改为按照userID存储,退出登录时先直接删除
        //这种方式是很不友好的方式,未来需要修改同app server的数据同步方式
        //SealUserInfoManager.getInstance().deleteAllUserInfo();*/
        //清除本app所有用户信息
        UserUtil.clearUserInfo(activity);
        //在mainActivity中是否需要重新注册消息数量监听， 只有被挤出融云后才需要
        mView.getSuccess("", 1);
    }

    @Override
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

}
