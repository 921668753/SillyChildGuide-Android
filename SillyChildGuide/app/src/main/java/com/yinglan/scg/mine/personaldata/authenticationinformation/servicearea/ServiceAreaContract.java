package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ServiceAreaContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取国家列表
         */
        void getCountryList();

        /**
         * 获取城市列表
         */
        void getCityList(int country_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


