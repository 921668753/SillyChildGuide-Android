package com.yinglan.scg.service;


import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface TravelCalendarContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取出行日历时间
         */
        void getDateList(Context context);

    }

    interface View extends BaseView<Presenter, String> {

    }

}