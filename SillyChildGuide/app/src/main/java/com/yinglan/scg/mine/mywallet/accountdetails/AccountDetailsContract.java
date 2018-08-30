package com.yinglan.scg.mine.mywallet.accountdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AccountDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取账户明细
         */
        void getAccountDetail(int page);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


