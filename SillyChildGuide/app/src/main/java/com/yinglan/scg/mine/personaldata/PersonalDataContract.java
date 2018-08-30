package com.yinglan.scg.mine.personaldata;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface PersonalDataContract {

    interface Presenter extends BasePresenter {


        /**
         * 获取个人信息
         */
        void getInfo();

        /**
         * 修改个人信息
         */
        void postMemberEdit(String imgUrl);
        // void postMemberEdit(String imgUrl, String sex, String nickName, String language, String remark, String photo);

        /**
         * 上传图片
         */
        void upPictures(String paramname);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
