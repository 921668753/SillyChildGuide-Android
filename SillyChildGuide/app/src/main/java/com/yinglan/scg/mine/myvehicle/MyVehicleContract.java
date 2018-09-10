package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface MyVehicleContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取我的车辆列表
         */
        void getMyVehicleList();

        /**
         * 设置默认车辆
         */
        void setModelDefault(int model_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
