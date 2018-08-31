package com.yinglan.scg.main;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;
import com.yinglan.scg.R;
import com.yinglan.scg.constant.StringNewConstants;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.message.SystemMessageFragment.MessageReceiver;
import com.yinglan.scg.message.interactivemessage.imuitl.RongCloudEvent;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.receivers.MainCallBack;
import com.yinglan.scg.receivers.MainReceiver;
import com.yinglan.scg.services.MainService;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

import static com.yinglan.scg.constant.StringNewConstants.MESSAGE_RECEIVED_ACTION;


@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity implements MainContract.View, MainCallBack {

    @BindView(id = R.id.bottombar_orderReceiving, click = true)
    private LinearLayout bottombar_orderReceiving;

    @BindView(id = R.id.img_orderReceiving)
    private ImageView img_orderReceiving;

    @BindView(id = R.id.tv_orderReceiving)
    private TextView tv_orderReceiving;

    @BindView(id = R.id.bottombar_service, click = true)
    private RelativeLayout bottombar_service;

    @BindView(id = R.id.img_service)
    private ImageView img_service;

    @BindView(id = R.id.tv_service)
    private TextView tv_service;

    @BindView(id = R.id.bottombar_message, click = true)
    private RelativeLayout bottombar_message;

    @BindView(id = R.id.tv_messageTag)
    public TextView tv_messageTag;

    @BindView(id = R.id.img_message)
    private ImageView img_message;

    @BindView(id = R.id.tv_message)
    private TextView tv_message;

    @BindView(id = R.id.bottombar_mine, click = true)
    private LinearLayout bottombar_mine;

    @BindView(id = R.id.img_mine)
    private ImageView img_mine;

    @BindView(id = R.id.tv_mine)
    private TextView tv_mine;


    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;
    private BaseFragment contentFragment3;
    private long firstTime = 0;


    private MessageReceiver mMessageReceiver;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;
    private Intent intentservice;

    public MainReceiver mainReceiver;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MainPresenter(this);
        contentFragment = new OrderReceivingFragment();
        contentFragment1 = new ServiceFragment();
        contentFragment2 = new MessageFragment();
        contentFragment3 = new MineFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
        registerMessageReceiver();  //   极光推送 used for receive msg
        mainReceiver = new MainReceiver(this);
        IntentFilter intentFilter = new IntentFilter(StringNewConstants.MainServiceAction);
        registerReceiver(mainReceiver, intentFilter);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        cleanColors(chageIcon);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 0);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(bottombar_orderReceiving, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(bottombar_service, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(bottombar_message, 160, 100);
        } else if (newChageIcon == 3) {
            setSimulateClick(bottombar_mine, 160, 100);
        } else {
            setSimulateClick(bottombar_orderReceiving, 160, 100);
        }
    }

    /**
     * 模拟点击
     *
     * @param view
     * @param x
     * @param y
     */
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.bottombar_orderReceiving:
                cleanColors(0);
                break;
            case R.id.bottombar_service:
                ((MainContract.Presenter) mPresenter).getIsLogin(aty, 0);
                break;
            case R.id.bottombar_message:
                ((MainContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.bottombar_mine:
                cleanColors(3);
                break;
            default:
                break;
        }
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
        //极光推送 定制声音、震动、闪灯等 Notification 样式。
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
//        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        img_orderReceiving.setImageResource(R.mipmap.tab_message);
        tv_orderReceiving.setTextColor(getResources().getColor(R.color.textColor));
        img_service.setImageResource(R.mipmap.tab_message);
        tv_service.setTextColor(getResources().getColor(R.color.textColor));
        img_message.setImageResource(R.mipmap.tab_message);
        tv_message.setTextColor(getResources().getColor(R.color.textColor));
        img_mine.setImageResource(R.mipmap.tab_personal);
        tv_mine.setTextColor(getResources().getColor(R.color.textColor));
        switch (chageIcon) {
            case 0:
                img_orderReceiving.setImageResource(R.mipmap.tab_message);
                tv_orderReceiving.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment);
                break;
            case 1:
                img_service.setImageResource(R.mipmap.tab_message);
                tv_service.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment1);
                break;
            case 2:
                img_message.setImageResource(R.mipmap.tab_message_selected);
                tv_message.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment2);
                break;
            case 3:
                img_mine.setImageResource(R.mipmap.tab_personal_selected);
                tv_mine.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment3);
                break;
            default:
                img_orderReceiving.setImageResource(R.mipmap.tab_message);
                tv_orderReceiving.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment);
                break;
        }


    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            cleanColors(1);
        } else if (flag == 1) {
            cleanColors(2);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0 && isLogin(msg) || flag == 1 && isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intentservice = new Intent(this, MainService.class);
        startService(intentservice);
    }

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    //如果两次按键时间间隔大于2秒，则不退出
                    ViewInject.toast(getString(R.string.clickAgainExitProgram));
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {
                    //  int i = 1 / 0;
                    //   KjBitmapUtil.getInstance().getKjBitmap().cleanCache();
                    MobclickAgent.onProfileSignOff();//关闭账号统计     退出登录也加
                    JPushInterface.stopCrashHandler(getApplication());//JPush关闭CrashLog上报
                    MobclickAgent.onKillProcess(aty);
                    if (!StringUtils.isEmpty(UserUtil.getResTokenInfo(this))) {
                        //在mainActivity中是否需要重新注册消息数量监听， 只有被挤出融云后才需要
                        //清除融云信息，退出登陆
                        RongIM.getInstance().logout();
                    }
                    Beta.unInit();
                    RongCloudEvent.getInstance().removeUnReadMessageCountChangedObserver();
                    //第一个参数为是否解绑推送的devicetoken
                    KJActivityStack.create().appExit(aty);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public void msgStyle(boolean havemsg) {
        if (havemsg) {
            tv_messageTag.setVisibility(View.VISIBLE);
        } else {
            tv_messageTag.setVisibility(View.GONE);
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

    @Override
    protected void onDestroy() {
        if (mMessageReceiver != null) {
            unregisterReceiver(mMessageReceiver);
            mMessageReceiver = null;
        }
        if (mainReceiver != null) {
            unregisterReceiver(mainReceiver);
            mainReceiver = null;
        }
        if (intentservice != null) {
            stopService(intentservice);
            intentservice = null;
        }
        super.onDestroy();
    }

}
