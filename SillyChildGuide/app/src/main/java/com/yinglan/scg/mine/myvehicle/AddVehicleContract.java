package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface AddVehicleContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取用户车辆详细信息
         */
        void getModelDetail(int model_id);

        /**
         * 司导上传车辆信息
         */
        void postEidtModel(int model_id, List<LocalMedia> selectList, String model_name_id, int passenger_number, int baggage_number, String model_year, int license_plate, int is_insurance);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
