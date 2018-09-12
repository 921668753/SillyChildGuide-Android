package com.yinglan.scg.orderreceiving.dialog;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface UnwillingnessTakeOrdersContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取取消订单的原因列表
         */
        void getCancelReasonList();

        /**
         * 取消订单操作
         */
        void postGuideSubmitOrder(String order_number, String cancel_reason);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
