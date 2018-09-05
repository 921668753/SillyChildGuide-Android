package com.yinglan.scg.mine.personaldata.authenticationinformation;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface AuthenticationInformationContract {

    interface Presenter extends BasePresenter {

        /**
         * 司导证件资料上传
         */
        void postEidtCertification(int city_id, List<LocalMedia> selectCarCompanyList, List<LocalMedia> selectDrivingLicenceList, List<LocalMedia> selectHoldCardFaceList,
                                   List<LocalMedia> selectLegallyList, List<LocalMedia> selectYachtDrivingLicenseList);


        void postEidtCertification1(int city_id, List<LocalMedia> selectCarCompanyList, List<LocalMedia> selectDrivingLicenceList, List<LocalMedia> selectHoldCardFaceList,
                                    List<LocalMedia> selectLegallyList, List<LocalMedia> selectYachtDrivingLicenseList);

        /**
         * 获取司导证件信息
         */
        void getCertificationDetail();

    }

    interface View extends BaseView<Presenter, String> {
    }

}
