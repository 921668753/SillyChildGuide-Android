package com.yinglan.scg.main;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomePageContract {
    interface Presenter extends BasePresenter {
//        /**
//         * 申请成为店长
//         */
//        void postHomePage(String store_logo, String store_name, String id_img);

//        /**
//         * 上传图片
//         */
//        void upPictures(String paramname);

        /**
         * 获取首页信息
         */
        void getHomePage(Context context);


        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


