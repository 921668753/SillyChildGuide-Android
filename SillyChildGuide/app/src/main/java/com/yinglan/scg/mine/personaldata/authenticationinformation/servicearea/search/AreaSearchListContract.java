package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AreaSearchListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getCityListByName(String name);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


