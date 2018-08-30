package com.yinglan.scg.message.systemmessage;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface SystemMessageListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取消息列表
         */
        void getSystemMessageList(String title, int page);

    }

    interface View extends BaseView<Presenter, String> {

    }


}
