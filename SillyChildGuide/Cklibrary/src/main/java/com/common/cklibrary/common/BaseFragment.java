package com.common.cklibrary.common;

import android.view.View;


import com.common.cklibrary.R;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.common.cklibrary.utils.rx.RxManager;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 公用的父Fragment
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/14.
 */

public abstract class BaseFragment extends KJFragment implements LoadingDialogView {


    public Object mPresenter = null;
    public Subscription subscription = null;
    private SweetAlertDialog mLoadingDialog;

    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText(title);
        }
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

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
    public void onPause() {
        super.onPause();
        //   RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        dismissLoadingDialog();
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


    /**
     * 必须此处创建订阅者 Subscription subscription
     */
    @Override
    protected void initData() {
        super.initData();
        subscription = RxBus.getInstance().register(MsgEvent.class).subscribe(new Action1<MsgEvent>() {
            @Override
            public void call(MsgEvent msgEvent) {
                callMsgEvent(msgEvent);
            }
        });
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (subscription != null && !subscription.isUnsubscribed()) {
            RxManager.get().add(this.getClass().getName(), subscription);
        }
    }


    public void callMsgEvent(MsgEvent msgEvent) {

    }

    @Override
    public void onDestroy() {
        RxVolley.getRequestQueue().cancelAll(getActivity().getClass().getName());
        RxManager.get().cancel(this.getClass().getName());
        super.onDestroy();
        subscription = null;
        mLoadingDialog = null;
        mPresenter = null;
    }
}
