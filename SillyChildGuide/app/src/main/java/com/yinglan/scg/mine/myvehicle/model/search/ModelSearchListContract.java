package com.yinglan.scg.mine.myvehicle.model.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ModelSearchListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getProductByAirportId(String name, int category);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


