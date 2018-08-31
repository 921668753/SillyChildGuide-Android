package com.yinglan.scg.loginregister.register;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.yinglan.scg.R;


/**
 * 注册协议
 * Created by ruitu ck on 2016/9/14.
 */

public class RegistrationAgreementActivity extends BaseActivity {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bannerdetails);
    }

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
        //   webViewLayout.setTitleText(title);
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                RegistrationAgreementActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
        //  if (!StringUtils.isEmpty(url)) {
       // webViewLayout.loadUrl(APIURLFORPAY + "/web/user/regProtocol");
        //    }
    }

}
