package com.yinglan.scg.mine.personaldata.setselfintroduction;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SetSelfIntroductionContract {
    interface Presenter extends BasePresenter {

        /**
         * 更改用户信息
         */
        void setSelfIntroduction(String personalized_signature);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


