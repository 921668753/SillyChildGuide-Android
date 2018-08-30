package com.common.cklibrary.utils.myview;

/**
 * Created by ruitu on 2016/10/11.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.R;


/**
 * 自定义Webview页面
 * 我的博客：http://blog.csdn.net/sinat_25689603
 * 作者：yedongyang
 * created by ydy on 2016/7/15 9:32
 */
public class WebViewLayout extends LinearLayout {

    private OnScrollChangedCallback mOnScrollChangedCallback;

    private LayoutInflater inflater;
    private View titleView;//头部
    private ProgressBar progressbar;//进度条
    private ObservableWebView webView;//网页

    private ImageView titleLeft;//返回
    private TextView titleText;//标题

    private boolean isUpdateTitle;//是否根据网页改变title
    private boolean isShowIconBack;//是否显示上一页下一页图标
    private boolean isJavaScriptEnabled;//是否允许JavaScript


    private WebViewCallBack callBack;//回调
    private RelativeLayout rl_title; //标题栏

    public WebViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WebViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WebViewLayout);
        isUpdateTitle = typedArray.getBoolean(R.styleable.WebViewLayout_isUpdateTitle, false);
//        isShowIconBack = typedArray.getBoolean(R.styleable.WebViewLayout_isShowIconBack, false);
        isJavaScriptEnabled = typedArray.getBoolean(R.styleable.WebViewLayout_isJavaScriptEnabled, false);
        typedArray.recycle();
        //添加头部
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        titleView = inflater.inflate(R.layout.webview_title_bar, null, false);
        titleView.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, dip2px(48)));
        rl_title = (RelativeLayout) titleView.findViewById(R.id.rl_title);
        titleLeft = (ImageView) titleView.findViewById(R.id.title_left);
        titleText = (TextView) titleView.findViewById(R.id.title_text);
        progressbar = (ProgressBar) titleView.findViewById(R.id.progress);
        addView(titleView);
        //添加webview
        webView = new ObservableWebView(context);
        webView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        /**
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(isJavaScriptEnabled);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //支持插件
        //    webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
     //   webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webView.setOnScrollChangedCallback(new OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (mOnScrollChangedCallback != null) {
                    mOnScrollChangedCallback.onScroll(dx, dy);
                }
            }
        });
        addView(webView);
        setTitleView();//设置标题栏
    }

    /**
     * 设置标题栏
     */
    private void setTitleView() {
        titleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null && back) {
                    webView.goBack();
                } else if (callBack != null && !back) {
                    callBack.backOnclick();
                }
            }
        });
    }

    private boolean back = false;


    public class WebViewClient extends android.webkit.WebViewClient {


        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // super.onReceivedSslError(view, handler, error);
            //注意：super句话一定要删除，或者注释掉，否则又走handler.cancel()默认的不支持https的了。
            //super.onReceivedSslError(view, handler, error);
            //handler.cancel(); // Android默认的处理方式
            //handler.handleMessage(Message msg); // 进行其他处理
            handler.proceed(); // 接受所有网站的证书
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (isUpdateTitle)
                titleText.setText(view.getTitle());
            back = view.canGoBack();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (callBack != null) {
                callBack.loadFailedError();
            }
        }
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    /**
     * 设置标题栏文字，只有在isUpdateTitle为false时有用
     */
    public void setTitleText(CharSequence text) {
//        if (!isUpdateTitle) {
        titleText.setText(text);
        // }
    }

    /**
     * 设置返回按钮
     */
    public void setTitleText(int textRes) {
//        if (!isUpdateTitle) {
        titleText.setText(textRes);
//        }
    }

    /**
     * 设置标题栏文字，只有在isUpdateTitle为false时有用
     */
    public void setBackImgResource(int textRes) {
        titleLeft.setImageResource(textRes);
    }

    public void setBackImgBitmap(Bitmap bitmap) {
        titleLeft.setImageBitmap(bitmap);
    }

    public void setBackImgDrawable(Drawable drawable) {
        titleLeft.setImageDrawable(drawable);
    }

    public void setBackImgURI(Uri uri) {
        titleLeft.setImageURI(uri);
    }


    /**
     * 设置标题栏是否隐藏
     */
    public void setTitleVisibility(boolean isVisible) {
        if (isVisible) {
            rl_title.setVisibility(View.VISIBLE);
        } else {
            rl_title.setVisibility(View.GONE);
        }
    }

    /**
     * 加载网页
     * created by ydy on 2016/7/15 10:14
     */
    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public void loadDataWithBaseURL(String baseUrl, String data,
                                    String mimeType, String encoding, String historyUrl) {
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void setWebViewCallBack(WebViewCallBack callBack) {
        this.callBack = callBack;
    }

    public interface WebViewCallBack {
        void backOnclick();

        void loadFailedError();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, Resources.getSystem().getDisplayMetrics());
        return (int) px;
    }


    public View getTitleView() {
        return titleView;
    }

    public TextView getTitleTextView() {
        return titleText;
    }

    public ImageView getTitleImgView() {
        return titleLeft;
    }

    public WebView getWebView() {
        return webView;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    class ObservableWebView extends WebView {
        private OnScrollChangedCallback mOnScrollChangedCallback1;

        public ObservableWebView(final Context context) {
            super(context);
        }

        public ObservableWebView(final Context context, final AttributeSet attrs) {
            super(context, attrs);
        }

        public ObservableWebView(final Context context, final AttributeSet attrs,
                                 final int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onScrollChanged(final int l, final int t, final int oldl,
                                       final int oldt) {
            super.onScrollChanged(l, t, oldl, oldt);

            if (mOnScrollChangedCallback1 != null) {
                mOnScrollChangedCallback1.onScroll(l - oldl, t - oldt);
            }
        }

        public OnScrollChangedCallback getOnScrollChangedCallback() {
            return mOnScrollChangedCallback1;
        }

        public void setOnScrollChangedCallback(
                final OnScrollChangedCallback onScrollChangedCallback) {
            mOnScrollChangedCallback1 = onScrollChangedCallback;
        }

    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback {
        public void onScroll(int dx, int dy);
    }
}