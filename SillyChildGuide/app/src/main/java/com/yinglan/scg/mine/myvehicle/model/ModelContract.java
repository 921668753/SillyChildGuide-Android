package com.yinglan.scg.mine.myvehicle.model;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ModelContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取国家列表
         */
        void getAirportCountryList();

        /**
         * 获取分类
         */
        void getAirportByCountryId(int country_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


