package com.yinglan.scg.message.interactivemessage.imuitl;


import android.app.ActivityManager;
import android.content.Context;

import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 获取Token
 */
public class RongIMUtil {

    // 融云文档提供:
    // 可搜索:关于 IMkit 中报“Rong SDK should not be initialized at subprocess”的解决方法
    public static String getCurProcessName(final Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static void connectRongIM(final Context context) {
        // 获取融云的Token
        String imToken = UserUtil.getResTokenInfo(context);
        if (!StringUtils.isEmpty(imToken)) {
            connect(imToken);
        }
    }

    private static void connect(final String rongYunToken) {

        // 连接融云服务器。
        try {
            RongIM.connect(rongYunToken, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(final String s) {
                    // 此处处理连接成功。
                    Log.d("RongYun", "Login successfully.");
                    RongCloudEvent.getInstance().setOtherListener();
                }

                @Override
                public void onError(final RongIMClient.ErrorCode errorCode) {
                    // 此处处理连接错误。
                    Log.d("RongYun", "Login failed.");
                }
            });
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
