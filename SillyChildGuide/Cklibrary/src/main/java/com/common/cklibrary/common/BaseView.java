package com.common.cklibrary.common;

/**
 * Created by ruitu on 2017/6/24.
 */


public interface BaseView<T, E> extends LoadingDialogView {

    @SuppressWarnings("unchecked")
    void setPresenter(T presenter);

    /**
     * http请求正确
     *
     * @param success 成功的信息
     * @param flag    用于区别请求
     */
    @SuppressWarnings("unchecked")
    void getSuccess(E success, int flag);

    /**
     * http请求错误
     */
    @SuppressWarnings("unchecked")
    void errorMsg(E msg, int flag);

}