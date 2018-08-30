package com.common.cklibrary.utils.httputil;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.Locale;

/**
 * Created by ruitu on 2016/8/25.
 */

public class HttpUtilParams {
    //volatile的作用是： 作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值.
    //一个定义为volatile的变量是说这变量可能会被意想不到地改变，
    //这样，编译器就不会去假设这个变量的值了。
    //精确地说就是，优化器在用到这个变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份。
    private volatile static HttpUtilParams httpUtilParams = null;
    private static HttpParams httpParams;

    //构造函数 逻辑处理

    private HttpUtilParams() {
        httpParams = new HttpParams();
//        httpParams.putHeaders("language", Locale.getDefault().getDisplayLanguage());//语言全称
        httpParams.putHeaders("language", Locale.getDefault().getLanguage());//语言简称
        httpParams.putHeaders("country", Locale.getDefault().getCountry());//国家简称
        httpParams.putHeaders("source", "android");
        httpParams.putHeaders("version", getVersion());
//        String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
//        Log.d("Cookies1", cookies);
//        if (!StringUtils.isEmpty(cookies)) {
//            Log.d("Cookies2", cookies);
//            httpParams.putHeaders("Cookie", cookies);
//        }
    }

    /**
     * 不是很好，待改进
     *
     * @return
     */
    public static HttpUtilParams getInstance() {
        dstroyInstance();
        //第一次判断是否为空
        if (httpUtilParams == null) {
            synchronized (HttpUtilParams.class) {//锁
                //第二次判断是否为空 多线程同时走到这里的时候，需要这样优化处理
                if (httpUtilParams == null) {
                    httpUtilParams = new HttpUtilParams();
                }
            }
        }

        return httpUtilParams;
    }
//    public synchronized static HttpUtilParams getInstance() {
//        httpUtilParams = null;
//        httpParams = null;
//        httpUtilParams = new HttpUtilParams();
//        return httpUtilParams;
//    }

    public HttpParams getHttpParams() {
        return httpParams;
    }

    private static void dstroyInstance() {
        if (httpUtilParams != null) {
            httpUtilParams = null;
        }
        if (httpParams != null) {
            httpParams = null;
        }
    }


    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public String getVersion() {
        try {
            PackageManager manager = KJActivityStack.create().topActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(KJActivityStack.create().topActivity().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "未获取到版本号";
        }
    }
}
