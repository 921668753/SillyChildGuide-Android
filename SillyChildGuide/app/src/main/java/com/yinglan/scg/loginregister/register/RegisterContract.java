package com.yinglan.scg.loginregister.register;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scg.entity.loginregister.LoginBean;

/**
 * Created by ruitu on 2016/9/24.
 */
interface RegisterContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String postCode);

        /**
         * 注册
         */
        void postRegister(String phone, String code, String pwd);

        /**
         * 登录融云
         */
        void loginRongYun(String rongYunToken, LoginBean bean);

    }

    interface View extends BaseView<Presenter, String> {

    }

}