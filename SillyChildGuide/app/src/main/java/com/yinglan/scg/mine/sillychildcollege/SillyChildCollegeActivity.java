package com.yinglan.scg.mine.sillychildcollege;

import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.yinglan.scg.R;


/**
 * 傻孩子学院
 */
public class SillyChildCollegeActivity extends BaseActivity {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenter);
    }


    @Override
    public void initData() {
        super.initData();
    }


    //    @Override
//    public void initWidget() {
//        super.initWidget();
//        initTitle();
//        webViewLayout.setTitleVisibility(false);
//     //   webViewLayout.loadUrl(URLConstants.ABOUTUSURL);
//
//    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }


    public void initView() {
        //   String title = getIntent().getStringExtra("title");
        //     String url = getIntent().getStringExtra("url");
        webViewLayout.setTitleText(getString(R.string.sillyChildCollege));
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                SillyChildCollegeActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
        //  if (!StringUtils.isEmpty(url)) {
        // webViewLayout.loadUrl(APIURLFORPAY + "/web/user/regProtocol");
        //    }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }
}
