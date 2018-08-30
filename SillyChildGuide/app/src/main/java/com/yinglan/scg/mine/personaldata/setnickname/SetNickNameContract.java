package com.yinglan.scg.mine.personaldata.setnickname;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SetNickNameContract {
    interface Presenter extends BasePresenter {
        /**
         * 修改个人信息
         */
        void postMemberEdit(String nickName);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


