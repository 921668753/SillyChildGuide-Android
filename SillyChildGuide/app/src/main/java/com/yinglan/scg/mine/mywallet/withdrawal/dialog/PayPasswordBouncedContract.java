package com.yinglan.scg.mine.mywallet.withdrawal.dialog;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * 支付密码弹框
 * Created by Administrator on 2017/2/21.
 */

public interface PayPasswordBouncedContract {


    interface Presenter extends BasePresenter {
        /**
         * 验证旧支付密码
         */
        void postOldPayPassword(String oldPaymentPassword);

        /**
         * 发送提现信息
         */
        void postWithdrawal(String withdrawalAmount, int bankId);

    }

    interface View extends BaseView<Presenter, String> {

    }

}
