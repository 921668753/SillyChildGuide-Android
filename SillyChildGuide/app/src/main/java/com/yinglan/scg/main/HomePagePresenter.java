package com.yinglan.scg.main;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View mView;

    public HomePagePresenter(HomePageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

//    @Override
//    public void upPictures(String path) {
//        if (StringUtils.isEmpty(path)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
//            return;
//        }
//        File oldFile = new File(path);
//        if (!(FileUtil.isFileExists(oldFile))) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
//            return;
//        }
//        long fileSize = 0;
//        try {
//            fileSize = DataCleanManager.getFileSize(oldFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fileSize = 0;
//        }
//        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
//            oldFile = BitmapCoreUtil.customCompression(oldFile);
//        }
//        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 0, new ResponseListener<String>() {
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
//    }

    @Override
    public void getHomePage(Context context) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getStoreInfo(context, httpParams, new ResponseListener<String>() {
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
