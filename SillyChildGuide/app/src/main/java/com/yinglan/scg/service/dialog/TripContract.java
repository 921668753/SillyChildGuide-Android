package com.yinglan.scg.service.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface TripContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取出行日历弹框数据
         */
        void getGuideOrderStroke(Context context, String time);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
