package com.yinglan.scg.mine.sharepolite;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yinglan.scg.R;
import com.yinglan.scg.constant.URLConstants;
import com.yinglan.scg.mine.sharepolite.dialog.ShareBouncedDialog;


/**
 * 分享有礼
 */
public class SharePoliteActivity extends BaseActivity implements WebViewLayout1.WebViewCallBack {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout1 webViewLayout;

    private String invite_code;

    private ShareBouncedDialog shareBouncedDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_sharepolite);
    }

    @Override
    public void initData() {
        super.initData();
        invite_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "invite_code");
        initShareBouncedDialog();
    }

    /**
     * 分享
     */
    private void initShareBouncedDialog() {
        shareBouncedDialog = new ShareBouncedDialog(this) {
            @Override
            public void share(SHARE_MEDIA platform) {
                umShare(platform);
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        webViewLayout.setTitleVisibility(false);
        webViewLayout.setWebViewCallBack(this);
        String url = URLConstants.SHARE + invite_code;
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.sharePolite), true, R.id.titlebar);
    }

    @Override
    public void backOnclick(String id) {
        //分享
        if (shareBouncedDialog == null) {
            initShareBouncedDialog();
        }
        if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
            shareBouncedDialog.show();
        }
    }

    @Override
    public void loadFailedError() {

    }

    /**
     * 直接分享
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb;
        thumb = new UMImage(this, R.mipmap.android_template);
        UMWeb web = new UMWeb(URLConstants.REGISTERHTML + invite_code);
        web.setTitle(getString(R.string.sillyChildMyHands));//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(getString(R.string.welcomSilly));//描述
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.notInstalled))) {
                ViewInject.toast(getString(R.string.notInstalled1));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            //  ViewInject.toast(getString(R.string.shareError));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (shareBouncedDialog != null && shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.dismiss();
                    return true;
                }
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }
}
