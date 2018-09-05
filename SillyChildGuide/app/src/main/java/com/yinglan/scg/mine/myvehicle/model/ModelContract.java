package com.yinglan.scg.mine.myvehicle.model;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ModelContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取车辆品牌列表
         */
        void getModelBrandList();

        /**
         * 获取车辆名称列表
         */
        void getModelNameList(int brand_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


