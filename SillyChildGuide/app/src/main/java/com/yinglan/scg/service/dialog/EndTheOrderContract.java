package com.yinglan.scg.service.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface EndTheOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 结束订单弹框
         */
        void postEndOrder(Context context, String order_number);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


