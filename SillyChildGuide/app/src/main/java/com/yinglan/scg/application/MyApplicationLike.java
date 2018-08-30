package com.yinglan.scg.application;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.glide.GlideCatchUtil;
import com.kymjs.common.FileUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.common.SystemTool;
import com.yinglan.scg.BuildConfig;
import com.yinglan.scg.R;

import com.yinglan.scg.message.interactivemessage.imuitl.RongCloudEvent;
import com.yinglan.scg.utils.ProcessUtil;
import com.yinglan.scg.utils.abnormal.LocalCrashHandler;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;


/**
 * 自定义ApplicationLike类.
 * <p>
 * 注意：这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里
 */
public class MyApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.MyApplicationLike";

    public MyApplicationLike(Application application, int tinkerFlags,
                             boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                             long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //错误日志保存本地，并强制重启程序
        LocalCrashHandler crashHandler = LocalCrashHandler.getInstance();
        crashHandler.init(getApplication());
        initBugly();
        initJPushInterface();
        initRongCloud();
        initPlatformConfig();
        initBaiDuSDK();
        testMemoryInfo();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // TODO: 安装tinker
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(
            Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }



    /**
     * 各个平台的配置，建议放在全局Application或者程序入口
     */
    private void initPlatformConfig() {
        UMConfigure.init(getApplication(), UMConfigure.DEVICE_TYPE_PHONE, BuildConfig.UMENG_APPKEY);
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(false);
        UMShareAPI.get(getApplication());//友盟分享
        // 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin(BuildConfig.WEIXIN_APPKEY, BuildConfig.WEIXIN_APPSECRET);
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(BuildConfig.QQ_APPID, BuildConfig.QQ_APPKEY);
        // 新浪
        PlatformConfig.setSinaWeibo(BuildConfig.SiNA_WEIBOKEY, BuildConfig.SiNA_WEIBOSECRET, "https://sns.whalecloud.com/sina2/callback");
    }


    /**
     * 极光推送所需
     */
    private void initJPushInterface() {
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(getApplication());            // 初始化 JPush
        JPushInterface.initCrashHandler(getApplication());// JPush开启CrashLog上报
        JPushInterface.getRegistrationID(getApplication());//获取设备RegistrationID   注：每个设备只有一个
        Log.d("JPush", JPushInterface.getRegistrationID(getApplication()));
    }

    /**
     * 百度定位以及地图所需
     */
    private void initBaiDuSDK() {
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
      //  SDKInitializer.initialize(getApplication());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        // SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    /**
     * 调用融云初始化方法
     */
    private void initRongCloud() {
        RongIM.init(getApplication());
        RongCloudEvent.init(getApplication());
    }


    /**
     * 腾讯Bugly初始化方法
     */
    private void initBugly() {
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        //   Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                //   Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                //     Toast.makeText(getApplication(),
//                        String.format(Locale.getDefault(), "%s %d%%",
//                                Beta.strNotificationDownloading,
//                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                // Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                //     Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                //   Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                //  Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };

        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), BuildConfig.DEBUG);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        //自动初始化开关  true表示app启动自动初始化升级模块; false不会自动初始化; 开发者如果担心sdk初始化影响app启动速度，可以设置为false，在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
        Beta.autoInit = true;
        //自动检查更新开关 true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
        Beta.autoCheckUpgrade = true;
        //升级检查周期设置  设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
        Beta.upgradeCheckPeriod = 5000;
        //延迟初始化  设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
        Beta.initDelay = 4 * 1000;
        //设置sd卡的Download为更新资源存储目录
        Beta.storageDir = FileUtils.getSaveFolder(StringConstants.DOWNLOADPATH);
        // 设置自定义tip弹窗UI布局
        Beta.upgradeDialogLayoutId = R.layout.dialog_upgrade;
        // 设置自定义升级对话框UI布局
        //  Beta.tipsDialogLayoutId = R.layout.dialog_tips;
        //设置点击过确认的弹窗在App下次启动自动检查更新时会再次显示。
        Beta.showInterruptedStrategy = true;
        //设置是否显示消息通知
        Beta.enableNotification = true;
        //设置通知栏大图标
        Beta.largeIconId = R.mipmap.android_template;
        //设置状态栏小图标
        Beta.smallIconId = R.mipmap.android_template;
        //设置更新弹窗默认展示的banner
        Beta.defaultBannerId = R.mipmap.android_template;
        //腾讯Bugly 异常上报
        Context context = getApplication().getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = ProcessUtil.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        strategy.setAppVersion(SystemTool.getAppVersionName(context));
        strategy.setAppPackageName(BuildConfig.APPLICATION_ID);  //App的包名
        strategy.setAppReportDelay(1000);   //改为20s
        String mobile = PreferenceHelper.readString(context, StringConstants.FILENAME, "mobile");
        if (!StringUtils.isEmpty(mobile)) {
            CrashReport.putUserData(context, "mobile", mobile);
        }
        // 初始化Bugly
        // CrashReport.initCrashReport(context, BuildConfig.TENGXUNBUGLY_APPID, BuildConfig.DEBUG, strategy);
        /**
         * 应用升级
         */
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), BuildConfig.TENGXUNBUGLY_APPID, BuildConfig.DEBUG, strategy);
    }


    //查询缓存
    public void testMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.d("tag", "largeMemoryClass = " + largeMemoryClass);
        Log.d("tag", "memoryClass = " + memoryClass);
        Log.d("tag", "availMem = " + (info.availMem / 1024 / 1024) + "M");
        Log.d("tag", "lowMemory = " + info.lowMemory);
        long size = GlideCatchUtil.getInstance().getCacheSize();
        if (size >= StringConstants.GLIDE_CATCH_SIZE) {
            // 必须在UI线程中调用
            GlideCatchUtil.getInstance().clearCacheMemory();
            // 必须在后台线程中调用，建议同时clearMemory()
            GlideCatchUtil.getInstance().clearCacheDiskSelf();
            GlideCatchUtil.getInstance().cleanCatchDisk();
        }
    }
}
