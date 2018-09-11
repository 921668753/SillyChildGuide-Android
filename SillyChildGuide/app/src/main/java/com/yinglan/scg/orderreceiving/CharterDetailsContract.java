package com.yinglan.scg.orderreceiving;

import android.app.Activity;
import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface CharterDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单的详细信息
         */
        void getTravelOrderDetails(String order_number);

        /**
         * 快速接单
         */
        void postGuideSubmitOrder(int model_id, String order_number);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
