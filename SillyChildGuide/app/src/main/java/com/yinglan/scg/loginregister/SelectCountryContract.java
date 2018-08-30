package com.yinglan.scg.loginregister;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface SelectCountryContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取国家区号
         */
        void getCountryNumber();


    }

    interface View extends BaseView<Presenter, String> {

    }

}