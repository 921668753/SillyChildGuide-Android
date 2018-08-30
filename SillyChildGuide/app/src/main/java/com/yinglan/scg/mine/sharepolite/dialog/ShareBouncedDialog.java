package com.yinglan.scg.mine.sharepolite.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.ViewInject;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yinglan.scg.R;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * 分享--------分享弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class ShareBouncedDialog extends Dialog implements View.OnClickListener {


    private Context context;

    public ShareBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sharebounced);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        LinearLayout ll_weChatFriends = (LinearLayout) findViewById(R.id.ll_weChatFriends);
        ll_weChatFriends.setOnClickListener(this);
        LinearLayout ll_circleFriends = (LinearLayout) findViewById(R.id.ll_circleFriends);
        ll_circleFriends.setOnClickListener(this);
        LinearLayout ll_QQFriends = (LinearLayout) findViewById(R.id.ll_QQFriends);
        ll_QQFriends.setOnClickListener(this);
        LinearLayout ll_sina = (LinearLayout) findViewById(R.id.ll_sina);
        ll_sina.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weChatFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) context, SHARE_MEDIA.WEIXIN)) {
                    ViewInject.toast(context.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_circleFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) context, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    ViewInject.toast(context.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.ll_QQFriends:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) context, SHARE_MEDIA.QQ)) {
                    ViewInject.toast(context.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_sina:
                if (!UMShareAPI.get(getApplication()).isInstall((Activity) context, SHARE_MEDIA.SINA)) {
                    ViewInject.toast(context.getString(R.string.authoriseErr2));
                    return;
                }
                dismiss();
                share(SHARE_MEDIA.SINA);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public abstract void share(SHARE_MEDIA platform);


}
