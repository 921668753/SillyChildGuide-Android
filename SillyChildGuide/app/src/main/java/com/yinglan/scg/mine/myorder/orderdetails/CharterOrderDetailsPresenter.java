package com.yinglan.scg.mine.myorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.common.cklibrary.utils.httputil.ResponseProgressbarListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.R;
import com.yinglan.scg.retrofit.RequestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruitu on 2018/9/24.
 */
public class CharterOrderDetailsPresenter implements CharterOrderDetailsContract.Presenter {
    private CharterOrderDetailsContract.View mView;

    public CharterOrderDetailsPresenter(CharterOrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取订单的详细信息
     */
    @Override
    public void getMyOrderDetails(String order_number) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_number", order_number);
        RequestClient.getMyOrderDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddReview(Context context, String order_number, String content, List<LocalMedia> selectList) {
        if (StringUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.evaluateGuest1), 1);
            return;
        }
        List<String> listStr = new ArrayList<String>();
        if (selectList.size() <= 0) {
            postAddReview1(context, order_number, content, listStr);
            return;
        }
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 1);
                return;
            }
            listStr.add("");
            if (selectList.get(i).getPath().startsWith("http")) {
                listStr.set(i, selectList.get(i).getPath());
                int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                selectListSize = selectListSize + 1;
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                if (selectListSize == selectList.size()) {
                    postAddReview1(context, order_number, content, listStr);
                }
                continue;
            }
            //参数 图片路径,图片名,token,成功的回调
            int finalI = i;
            RequestClient.upLoadImg(KJActivityStack.create().topActivity(), selectList.get(i).getPath(), 1, new ResponseProgressbarListener<String>() {
                @Override
                public void onProgress(String progress) {
                    //  mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad) + progress + "%");
                }

                @Override
                public void onSuccess(String response) {
                    int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                    selectListSize = selectListSize + 1;
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                    listStr.set(finalI, response);
                    if (selectListSize == selectList.size()) {
                        postAddReview1(context, order_number, content, listStr);
                        selectList.clear();
                    }
                }

                @Override
                public void onFailure(String msg) {
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(msg, 1);
                            return;
                        }
                    });
                }
            });
        }
    }


    public void postAddReview1(Context context, String order_number, String content, List<String> selectList) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        String imgsStr = "";
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i);
            }
            map.put("pictures", imgsStr.substring(1));
        } else {
            map.put("pictures", imgsStr);
        }
        map.put("order_number", order_number);
        map.put("content", content);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map));
        RequestClient.postAddReview(context, httpParams, new ResponseListener<String>() {
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
