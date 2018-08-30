package com.common.cklibrary.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.common.cklibrary.R;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.common.cklibrary.utils.rx.RxManager;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 公用的父Activity
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/13.
 */

public abstract class BaseActivity extends KJActivity implements LoadingDialogView {
    public Object mPresenter = null;
    public Subscription subscription = null;
    private SweetAlertDialog mLoadingDialog = null;

    /**
     * 解决framenet重影
     *
     * @param outState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //  super.onSaveInstanceState(outState);
    }

//    /**
//     * session的统计
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //   MobclickAgent.onResume(this);
//    }

    /**
     * 必须此处创建订阅者 Subscription subscription
     */
    @Override
    public void initData() {
        super.initData();
        subscription = RxBus.getInstance().register(MsgEvent.class).subscribe(new Action1<MsgEvent>() {
            @Override
            public void call(MsgEvent msgEvent) {
                callMsgEvent(msgEvent);
            }
        });
    }


    @Override
    public void initWidget() {
        super.initWidget();
        if (subscription != null && !subscription.isUnsubscribed()) {
            try {
                RxManager.get().add(this.getClass().getName(), subscription);
            } catch (NoClassDefFoundError noClassDefFoundError) {
                Log.d("Error", "Android版本过低");
            }
        }
    }

    /**
     * @param title show Dialog
     */
    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    /**
     * 关闭 Dialog
     */
    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        //关闭 Dialog
        dismissLoadingDialog();
        //取消网络请求
        //    RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        //    MobclickAgent.onPause(this);
    }

    /**
     * 是否没登录
     *
     * @return
     */
    public boolean isLogin(String mag) {
        if (StringUtils.isEmpty(mag) || mag != null && mag.equals("-10001")) {
            return true;
        }
        return false;
    }


    public void callMsgEvent(MsgEvent msgEvent) {

    }

    /**
     * 页面销毁时取消订阅，防止内存溢出  Subscription subscription
     */
    @Override
    protected void onDestroy() {
        RxVolley.getRequestQueue().cancelAll(this.getClass().getName().getClass().getName());
        RxManager.get().cancel(this.getClass().getName());
        super.onDestroy();
        subscription = null;
        mLoadingDialog = null;
        mPresenter = null;
    }
}
