package com.yinglan.scg.message.interactivemessage;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ConversationContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取用户信息信息
         */
        void getUserInfo(String targetId);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


