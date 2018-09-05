package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface VehicleDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取用户车辆详细信息
         */
        void getModelDetail(int model_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
