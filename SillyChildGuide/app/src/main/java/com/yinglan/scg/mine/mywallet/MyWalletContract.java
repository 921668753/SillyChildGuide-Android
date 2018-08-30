package com.yinglan.scg.mine.mywallet;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface MyWalletContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取钱包余额
         */
        void getMyWallet();

    }

    interface View extends BaseView<Presenter, String> {
    }

}
