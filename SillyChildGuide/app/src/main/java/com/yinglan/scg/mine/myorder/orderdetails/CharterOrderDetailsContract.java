package com.yinglan.scg.mine.myorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterOrderDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取订单的详细信息
         */
        void getMyOrderDetails(String order_number);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


