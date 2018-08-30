package com.yinglan.scg.message.interactivemessage.imuitl;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yinglan.scg.main.MainActivity;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

import static com.yinglan.scg.constant.StringNewConstants.MainServiceAction;

/**
 * 为了接收推送消息，您需要自定义一个继承自 PushMessageReceiver 类的 BroadcastReceiver (必须实现,否则会收不到推送消息)，
 * 实现其中的 onNotificationMessageArrived，onNotificationMessageClicked 然后把该 receiver 注册到 AndroidManifest.xml 文件中。
 * 自定义的 BroadcastReceiver：
 */
public class RongIMPushMessageReceiver extends PushMessageReceiver {


    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        Log.d("RY", "[RongIMPushMessageReceiver] 用户点击打开了通知");
        //打开自定义的Activity
        Intent news = new Intent(context, MainActivity.class);
        news.putExtra("chageIcon", 1);
        news.putExtra("newChageIcon", 1);
        news.putExtra("chageMessageIcon", 20);
        news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(news);
        news.setAction(MainServiceAction);
        news.putExtra("havemsg", false);
        context.sendBroadcast(news);
//        Intent intent = new Intent();
//        intent.putExtra("newChageIcon", 1);
//        intent.putExtra("chageIcon", 20);
//        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri.Builder uriBuilder = Uri.parse("rong://" + context.getPackageName()).buildUpon();
//        uriBuilder.appendPath("push_message")
//                .appendQueryParameter("targetId", pushNotificationMessage.getTargetId())
//                .appendQueryParameter("pushData", pushNotificationMessage.getPushData())
//                .appendQueryParameter("pushId", pushNotificationMessage.getPushId())
//                .appendQueryParameter("extra", pushNotificationMessage.getExtra());
//        context.startActivity(intent);
        return true;
    }
}
