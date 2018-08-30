package com.yinglan.scg.message.systemmessage;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SystemMessageDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取系统信息详情
         */
        void getSystemMessageDetails(int id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


