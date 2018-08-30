package com.yinglan.scg.message.interactivemessage.imuitl;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.entity.RongCloudBean;
import com.yinglan.scg.retrofit.RequestClient;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 用户信息提供者的异步请求类
 * Created by AMing on 15/12/10.
 * Company RongCloud
 */
public class UserInfoEngine {

    private static UserInfoEngine instance;
    private UserInfoListener mListener;

    public static UserInfoEngine getInstance(Context context) {
        if (instance == null) {
            instance = new UserInfoEngine(context);
        }
        return instance;
    }

    private UserInfoEngine(Context context) {
        this.context = context;
    }

    private static Context context;

    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo startEngine(String userid) {
        setUserid(userid);
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("userId", userid);
        RequestClient.getRongCloud(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(response, RongCloudBean.class);
                if (RongIM.getInstance() != null && rongCloudBean.getData() != null && !StringUtils.isEmpty(rongCloudBean.getData().getFace())) {
                    userInfo = new UserInfo(userid + "", rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
                    if (mListener != null) {
                        mListener.onResult(userInfo);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                Log.d("RongYun", "onFailure");
            }
        });
        return getUserInfo();
    }


    public void setListener(UserInfoListener listener) {
        this.mListener = listener;
    }

    public interface UserInfoListener {
        void onResult(UserInfo info);
    }
}