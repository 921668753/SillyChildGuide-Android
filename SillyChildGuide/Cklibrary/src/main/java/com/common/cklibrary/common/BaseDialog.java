package com.common.cklibrary.common;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import com.common.cklibrary.R;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/9/7.
 */

public abstract class BaseDialog extends Dialog implements LoadingDialogView{

    public SweetAlertDialog mLoadingDialog = null;
    public Context mContext;
    public Object mPresenter = null;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    /**
     * @param title show Dialog
     */
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(com.common.cklibrary.R.color.dialogLoadingColor));
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

    @Override
    public void dismiss() {
        RxVolley.getRequestQueue().cancelAll(mContext.getClass().getName());
        super.dismiss();
//        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
//            try {
//                mLoadingDialog.dismiss();
//            } catch (Exception e) {
//                mLoadingDialog = null;
//            }
//        }
//        mLoadingDialog = null;
    }
}
