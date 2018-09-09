package com.yinglan.scg.orderreceiving;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MissedOrdersContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取首页信息
         */
        void getHomePage(Context context);


        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


