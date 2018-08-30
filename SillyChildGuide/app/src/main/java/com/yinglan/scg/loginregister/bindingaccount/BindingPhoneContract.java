package com.yinglan.scg.loginregister.bindingaccount;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scg.entity.loginregister.LoginBean;

/**
 * Created by ruitu on 2016/9/24.
 */
interface BindingPhoneContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String postCode);


        /**
         * 绑定手机号
         */
        void postBindingPhone(String openid, String from, String phone, String code, String recommendcode);

        /**
         * 登录融云
         */
        void loginRongYun(String rongYunToken, LoginBean bean);

    }

    interface View extends BaseView<Presenter, String> {

    }

}