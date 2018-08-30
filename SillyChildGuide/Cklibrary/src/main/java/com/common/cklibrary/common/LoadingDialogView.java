package com.common.cklibrary.common;

/**
 * Created by ck on 2017/3/6.
 */

public interface LoadingDialogView {
    /**
     * @param title show Dialog
     */

    void showLoadingDialog(String title);

    /**
     * 关闭 Dialog
     */
    void dismissLoadingDialog();
}
