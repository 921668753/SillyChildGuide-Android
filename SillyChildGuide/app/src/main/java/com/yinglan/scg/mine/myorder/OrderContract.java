package com.yinglan.scg.mine.myorder;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取我的订单列表
         */
        void getMyOrderPage(Context context, String status, int pageno);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


