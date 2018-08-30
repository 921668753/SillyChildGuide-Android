package com.yinglan.scg.mine.mywallet.mybankcard;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface MyBankCardContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取银行卡列表
         */
        void getMyBankCard();

        /**
         * 设置默认银行卡
         */
        void postPurseDefault(int id);

        /**
         * 删除银行卡
         */
        void postRemoveBank(int id);
    }

    interface View extends BaseView<Presenter, String> {

    }


}
