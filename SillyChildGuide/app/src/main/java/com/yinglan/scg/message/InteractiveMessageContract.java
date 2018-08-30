package com.yinglan.scg.message;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Admin on 2017/9/21.
 */

public class InteractiveMessageContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flage);

    }

    interface View extends BaseView<Presenter, String> {
    }

}