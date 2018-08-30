package com.yinglan.scg.message.interactivemessage.imuitl;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yinglan.scg.message.interactivemessage.imuitl.moudle.SealExtensionModule;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

import static com.yinglan.scg.constant.StringNewConstants.MainServiceAction;

/**
 * 融云SDK事件监听处理。 把事件统一处理，开发者可直接复制到自己的项目中去使用。
 * <p/>
 * 该类包含的监听事件有：
 * 1、会话列表界面操作的监听器：       ConversationListBehaviorListener。
 * 2、接收消息的监听器：             OnReceiveMessageListener。
 * 3、用户信息的提供者：             UserInfoProvider。
 * 4、群组信息的提供者：             GroupUserInfoProvider。
 * 5、GroupUserInfo提供者：          GroupUserInfoProvider。
 * 6、连接状态监听器，以获取连接相关状态：    ConnectionStatusListener。
 * 7、地理位置提供者：                 LocationProvider。
 * 8、会话界面操作的监听器：            ConversationBehaviorListener。
 * 9、未读消息数监听器的监听器：           addUnReadMessageCountChangedObserver。
 */
public final class RongCloudEvent implements
        RongIM.ConversationListBehaviorListener,
        RongIMClient.OnReceiveMessageListener,
        RongIM.UserInfoProvider,
        //  RongIM.GroupInfoProvider,
        RongIM.GroupUserInfoProvider,
        RongIMClient.ConnectionStatusListener,
        RongIM.LocationProvider,
        RongIM.ConversationBehaviorListener,
        RongIM.OnSendMessageListener, IUnReadMessageObserver {

    private static final String TAG = RongCloudEvent.class.getSimpleName();

    private static RongCloudEvent mRongCloudInstance;

    private final Context mContext;

    /**
     * 初始化 RongCloud.
     *
     * @param context 上下文。
     */
    public static void init(final Context context) {
        if (mRongCloudInstance == null) {
            synchronized (RongCloudEvent.class) {
                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new RongCloudEvent(context);
                }
            }
        }

    }

    /**
     * 构造方法。
     *
     * @param context 上下文。
     */
    private RongCloudEvent(final Context context) {
        mContext = context;
        initDefaultListener();
    }

    /**
     * RongIM.init(this) 后直接可注册的Listener。
     */
    private void initDefaultListener() {
        RongIM.setConversationBehaviorListener(this);//设置会话界面操作的监听器。
        RongIM.setConversationListBehaviorListener(this);//设置会话列表界面操作的监听器
        RongIM.setUserInfoProvider(this, true);// 设置用户信息提供者。
        //  RongIM.setGroupInfoProvider(this, true);// 设置群组信息提供者。
        RongIM.setLocationProvider(this);//设置地理位置提供者,不用位置的同学可以注掉此行代码
        setReadReceiptConversationType();
        RongIMUtil.connectRongIM(mContext);
    }


    /*
     * 连接成功注册。 <p/> 在RongIM-connect-onSuccess后调用。
     */
    public void setOtherListener() {
        RongIM.setOnReceiveMessageListener(this);// 设置发出消息接收监听器.
        RongIM.setConnectionStatusListener(this);// 设置连接状态监听器。
        setUserInfoEngineListener();   //用户信息提供者回调监听
//    setGroupInfoEngineListener();  //群组信息提供者回调监听
        setInputProvider();
    }

    private void setReadReceiptConversationType() {
        Conversation.ConversationType[] types = new Conversation.ConversationType[]{
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.DISCUSSION
        };
        RongIM.getInstance().setReadReceiptConversationTypeList(types);
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, types);
    }

    private void setInputProvider() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        ;
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new SealExtensionModule());
            }
        }
    }


    /**
     * 获取RongCloud 实例。
     *
     * @return RongCloud。
     */
    public static RongCloudEvent getInstance() {
        return mRongCloudInstance;
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus) {
            case CONNECTED://连接成功。

                break;
            case DISCONNECTED://断开连接。

                break;
            case CONNECTING://连接中。

                break;
            case NETWORK_UNAVAILABLE://网络不可用。

                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                UserUtil.clearUserInfo(mContext);
                break;
        }
    }

    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s) {
        return false;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    @Override
    public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
        return false;
    }

    @Override
    public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
        return false;
    }

    @Override
    public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
        return false;
    }

    @Override
    public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
        return false;
    }


    @Override
    public GroupUserInfo getGroupUserInfo(String s, String s1) {
        return null;
    }

    @Override
    public void onStartLocation(Context context, LocationCallback locationCallback) {

    }

    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }

    @Override
    public UserInfo getUserInfo(String s) {
        return UserInfoEngine.getInstance(mContext).startEngine(s);
    }

    @Override
    public Message onSend(Message message) {
        return null;
    }

    @Override
    public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
        return false;
    }

    /**
     * 需要 rongcloud connect 成功后设置的 listener
     */
    public void setUserInfoEngineListener() {
        UserInfoEngine.getInstance(mContext).setListener(new UserInfoEngine.UserInfoListener() {
            @Override
            public void onResult(UserInfo info) {
                if (info != null && RongIM.getInstance() != null) {
                    RongIM.getInstance().refreshUserInfoCache(info);
                }
            }
        });
    }

    /**
     * 未读消息数监听器
     *
     * @param i
     */
    @Override
    public void onCountChanged(int i) {
        Intent news = new Intent();
        news.setAction(MainServiceAction);
        if (i > 0) {
            news.putExtra("havemsg", true);
        } else {
            news.putExtra("havemsg", false);
        }
        mContext.sendBroadcast(news);
    }

    public void removeUnReadMessageCountChangedObserver() {
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
    }
}