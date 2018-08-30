package com.yinglan.scg.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.constant.StringNewConstants;
import com.yinglan.scg.entity.message.SystemMessageBean;
import com.yinglan.scg.retrofit.RequestClient;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2018/6/27.
 */
public class MainService extends Service {

    private Thread mThread = null;

    private Intent intentcast;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (mThread != null && !mThread.isAlive()) {
//            mThread.run();
//        } else {
//            mThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
        getSystemMessage();
//                }
//            });
//            mThread.start();
        //  }
        return super.onStartCommand(intent, flags, startId);
    }

    public void getSystemMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getSystemMessage(this, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(response, SystemMessageBean.class);
                if (systemMessageBean.getData() == null || systemMessageBean.getData().size() <= 0) {
                    getRongIMMessage();
                    return;
                }
                int num = 0;
                if (systemMessageBean.getData() != null && systemMessageBean.getData().size() > 0) {
                    for (int i = 0; i < systemMessageBean.getData().size(); i++) {
                        num += systemMessageBean.getData().get(i).getNum();
                    }
                }
                if (num > 0) {
                    sendCast(true);
                    return;
                }
                getRongIMMessage();
            }

            @Override
            public void onFailure(String msg) {
                getRongIMMessage();
            }
        });
    }

    /**
     * 获取融云未读消息数量
     */
    public void getRongIMMessage() {
        int num = RongIM.getInstance().getTotalUnreadCount();
        if (num > 0) {
            sendCast(true);
            return;
        }
        sendCast(false);
    }

    /**
     * 发送广播
     *
     * @param havemsg
     */
    private void sendCast(boolean havemsg) {
        if (intentcast == null)
            intentcast = new Intent(StringNewConstants.MainServiceAction);
        intentcast.putExtra("havemsg", havemsg);
        sendBroadcast(intentcast);
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mThread != null) {
//            mThread.interrupted();
//        }
        mThread = null;
    }
}
