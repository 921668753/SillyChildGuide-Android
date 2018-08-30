package com.common.cklibrary.utils.httputil;


import android.content.Context;
import android.util.Log;

import com.common.cklibrary.R;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.NetworkUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.RequestConfig;

import java.util.Map;

//import com.kymjs.rxvolley.client.ProgressListener;

/**
 * Created by Administrator on 2017/3/8.
 */

public class HttpRequest {

    private final static int TOLINGIN = -10001;
    private final static int SUCCESS = 1;

    //  private static RxCookieVolley.Builder builder = null;
    private static RxVolley.Builder builder2;

    public static void requestHttp(String url, final Context context, final int httpMethod, int contentType, HttpParams params, boolean isCache, final ResponseListener responseListener) {
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            responseListener.onFailure(KJActivityStack.create().topActivity().getString(R.string.checkNetwork));
            return;
        }
        RxCookieVolley.Builder builder = new RxCookieVolley.Builder();

        //http请求的回调，内置了很多方法，详细请查看源码
//包括在异步响应的onSuccessInAsync():注不能做UI操作
//网络请求成功时的回调onSuccess()
//网络请求失败时的回调onFailure():例如无网络，服务器异常等
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                if (headers != null && headers.size() > 0 && !StringUtils.isEmpty(headers.get("Set-Cookie")) && headers.get("Set-Cookie").length() > 70) {
                    PreferenceHelper.write(context, StringConstants.FILENAME, "Cookie", headers.get("Set-Cookie"));
                    Log.d("Cookies", JsonUtil.obj2JsonString(headers));
                }
                doSuccess(new String(t), responseListener);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                doFailure(errorNo, strMsg, responseListener);
            }
        };
        builder.url(url) //接口地址
                //请求类型，如果不加，默认为 GET 可选项：
                //POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
                .httpMethod(httpMethod)
//                .timeout(1000 * 60 * 1000)
                .timeout(1000 * 60 * 1)
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(1000 * 60 * 1)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(contentType)
                .params(params) //上文创建的HttpParams请求参数集
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(isCache)
                .setTag(context.getClass().getName())
                //    .progressListener(listener) //上传进度
                .callback(callback) //响应回调
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestGetHttp(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.GET, RxVolley.ContentType.FORM, params, false, responseListener);
    }

    /**
     * get请求,不使用KJActivityStack
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestGetHttpWithContext(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttpWithContext(context, url, RxVolley.Method.GET, RxVolley.ContentType.FORM, params, false, responseListener);
    }

    public static void requestGetHttp(Context context, String url, HttpParams params, boolean isCache, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.GET, RxVolley.ContentType.FORM, params, isCache, responseListener);
    }

    /**
     * postjosn请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestPostHttp(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.POST, RxVolley.ContentType.JSON, params, false, responseListener);
    }

    public static void requestPostFORMHttp(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.POST, RxVolley.ContentType.FORM, params, false, responseListener);
    }

    /**
     * PUTjosn请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestPutHttp(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.PUT, RxVolley.ContentType.FORM, params, false, responseListener);
    }

    /**
     * DELETEjosn请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestDeleteHttp(Context context, String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, context, RxVolley.Method.DELETE, RxVolley.ContentType.FORM, params, false, responseListener);
    }


    /**
     * 网络请求成功
     *
     * @param s
     * @param listener
     */
    @SuppressWarnings("unchecked")
    public static boolean doSuccess(String s, ResponseListener listener) {
        if (s == null) {
            listener.onFailure(KJActivityStack.create().bottomActivity().getString(R.string.serverReturnsDataNullJsonError));
            return false;
        }
        BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(s, BaseResult.class);
        if (baseResult == null) {
            listener.onFailure(KJActivityStack.create().bottomActivity().getString(R.string.jsonError));
            return false;
        }
        if (baseResult.getResult() != SUCCESS) {
            if (baseResult.getResult() == -100 || baseResult.getResult() == -101 || baseResult.getResult() == 401) {
                listener.onFailure(TOLINGIN + "");
                return false;
            }
            if (baseResult.getResult() == 4000) {
                listener.onFailure("4000");
                return false;
            }
            listener.onFailure(baseResult.getMessage());
            return false;
        }
//        if (baseResult.getResult() == null || JsonUtil.obj2JsonString(baseResult.getResult()) == null || JsonUtil.obj2JsonString(baseResult.getResult()).length() <= 2) {
//            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverReturnsDataNull));
//            return false;
//        }
        listener.onSuccess(s);
        return true;
    }

    /**
     * 接口返回失败信息处理
     *
     * @param errorMsg
     * @param listener
     */
    public static void doFailure(int errCode, String errorMsg, ResponseListener listener) {
        Log.d("tag", errCode + "错误原因：" + errorMsg);
        if (errCode == -1) {
            if (isEmpty(errorMsg)) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.clientError));
            } else if (errorMsg.contains("java.lang.IllegalArgumentException")) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.illegalArgumentException));
            } else if (errorMsg.contains("NetWork err") || errorMsg.contains("NoConnection error")) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.checkNetwork));
            } else {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.clientError));
            }
        } else if (errCode == TOLINGIN) {
            listener.onFailure(TOLINGIN + "");
        } else if (errCode == 400) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.grammarError));
        } else if (errCode == 401 || errCode == 407) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.authenticationError));
        } else if (errCode == 403) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverRejectsRequest));
        } else if (errCode == 404) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.urlError));
        } else if (errCode > 404 && errCode < 500) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.requestError));
        } else if (errCode >= 500) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverError));
        } else {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.otherError));
        }
    }


    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence... strs) {
        for (CharSequence str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不使用KJActivityStack的网络请求
     *
     * @param url
     * @param httpMethod
     * @param contentType
     * @param params
     * @param isCache
     * @param responseListener
     */
    public static void requestHttpWithContext(Context context, String url, int httpMethod, int contentType, HttpParams params, boolean isCache, final ResponseListener responseListener) {
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            responseListener.onFailure(context.getString(R.string.checkNetwork));
            return;
        }
        if (builder2 == null) {
            builder2 = new RxVolley.Builder();
        }
        //http请求的回调，内置了很多方法，详细请查看源码
//包括在异步响应的onSuccessInAsync():注不能做UI操作
//网络请求成功时的回调onSuccess()
//网络请求失败时的回调onFailure():例如无网络，服务器异常等
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                builder2 = null;
                doSuccess(t, responseListener);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                builder2 = null;
                doFailure(errorNo, strMsg, responseListener);
            }
        };
        builder2.url(url) //接口地址
                //请求类型，如果不加，默认为 GET 可选项：
                //POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
                .httpMethod(httpMethod)
//                .timeout(1000 * 60 * 1000)
                .timeout(1000 * 60 * 1)
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
//                .cacheTime(1)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(contentType)
                .params(params) //上文创建的HttpParams请求参数集
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(isCache)
                .setTag(context.getClass().getName())
                //    .progressListener(listener) //上传进度
                .callback(callback) //响应回调
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }


}
