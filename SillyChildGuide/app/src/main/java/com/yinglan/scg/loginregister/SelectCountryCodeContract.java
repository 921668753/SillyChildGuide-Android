package com.yinglan.scg.loginregister;


import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface SelectCountryCodeContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取国家区号
         */
        void getCountryNumber(Context context);


    }

    interface View extends BaseView<Presenter, String> {

    }

}