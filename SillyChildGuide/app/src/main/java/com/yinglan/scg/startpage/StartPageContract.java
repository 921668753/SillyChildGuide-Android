package com.yinglan.scg.startpage;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public interface StartPageContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取配置参数
         */
        void getAppConfig();

        /**
         * 设置定位信息
         */
    //    void initLocation(Activity activity, LocationClient mLocationClient);

        /**
         * 获取未读系统消息数量
         */
        void getSystemMessage();

        /**
         * 获取未读司导消息数量
         */
        void getGuideMessage();

        /**
         * 获取未读司导消息数量
         */
        void getChatManagerListener();


        /**
         * 获取上传图片token
         */
        void getQiNiuKey();

    }

    @SuppressWarnings("unchecked")
    interface View extends BaseView<Presenter, String> {

    }
}
