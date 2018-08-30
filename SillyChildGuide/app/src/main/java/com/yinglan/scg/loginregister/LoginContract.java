package com.yinglan.scg.loginregister;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scg.entity.loginregister.LoginBean;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter {

        /**
         * 账号登录
         */
        void postToLogin(String phone, String pwd);

//        /**
//         * 获取用户信息
//         */
//        void getInfo();

        /**
         * 登录融云
         */
        void loginRongYun(String rongYunToken, LoginBean bean);

        /**
         * 第三方账号登录
         */
        void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


