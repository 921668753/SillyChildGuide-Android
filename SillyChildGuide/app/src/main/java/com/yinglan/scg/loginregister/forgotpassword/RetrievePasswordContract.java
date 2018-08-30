package com.yinglan.scg.loginregister.forgotpassword;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface RetrievePasswordContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String postCode);

        /**
         * 重置密码请求
         */
        void postResetpwd(String phone, String code, String pwd, String pwd1);


    }

    interface View extends BaseView<Presenter, String> {

    }

}