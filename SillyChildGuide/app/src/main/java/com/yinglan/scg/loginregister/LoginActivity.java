package com.yinglan.scg.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareConfig;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.loginregister.LoginBean;
import com.yinglan.scg.loginregister.bindingaccount.BindingPhoneActivity;
import com.yinglan.scg.loginregister.forgotpassword.RetrievePasswordActivity;
import com.yinglan.scg.loginregister.register.RegisterActivity;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

/**
 * 登录
 * Created by ruitu ck on 2016/9/14.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    /**
     * 账号
     */
    @BindView(id = R.id.et_accountNumber)
    private EditText et_accountNumber;

    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;

    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao1, click = true)
    private ImageView img_quxiao1;
    @BindView(id = R.id.img_yanjing, click = true)
    private ImageView img_yanjing;

    /**
     * 忘记密码
     */
    @BindView(id = R.id.tv_forgotPassword, click = true)
    private TextView tv_forgotPassword;
    /**
     * 登录
     */
    @BindView(id = R.id.tv_login, click = true)
    private TextView tv_login;

    /**
     * 注册
     */
    @BindView(id = R.id.ll_register, click = true)
    private LinearLayout ll_register;

    /**
     * 微信
     */
    @BindView(id = R.id.img_loginweixin, click = true)
    private ImageView img_loginweixin;

    /**
     * QQ
     */
    @BindView(id = R.id.img_loginqq, click = true)
    private ImageView img_loginqq;

    private String openid;
    private String from;
    private String nickname;
    private String head_pic;
    private int sex = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new LoginPresenter(this);
    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        tv_login.setClickable(false);//不可点击
        changeInputView(et_accountNumber, img_quxiao);
        changeInputView(et_pwd, img_quxiao1);
    }


    /**
     * view监听事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_forgotPassword:
                showActivity(aty, RetrievePasswordActivity.class);
                break;
            case R.id.tv_login:
                showLoadingDialog(getString(R.string.loggingLoad));
                ((LoginContract.Presenter) mPresenter).postToLogin(et_accountNumber.getText().toString(), et_pwd.getText().toString());
                break;
            case R.id.img_quxiao:
                et_accountNumber.setText("");
                break;
            case R.id.img_quxiao1:
                et_pwd.setText("");
                break;
            case R.id.img_yanjing:
                if (et_pwd.getInputType() == 0x00000081) {
                    img_yanjing.setImageResource(R.mipmap.yanjing1);
                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    img_yanjing.setImageResource(R.mipmap.yanjing);
                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pwd.setSelection(et_pwd.getText().toString().trim().length());
                et_pwd.requestFocus();
                break;
            case R.id.ll_register:
                showActivity(aty, RegisterActivity.class);
                break;
            case R.id.img_loginweixin:
                thirdLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.img_loginqq:
                thirdLogin(SHARE_MEDIA.QQ);
                break;
            default:
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", et_accountNumber.getText().toString());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "face", bean.getData().getFace());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "username", bean.getData().getUsername());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "rongYunToken", bean.getData().getRong_cloud());
            ((LoginContract.Presenter) mPresenter).loginRongYun(bean.getData().getRong_cloud(), bean);
        } else if (flag == 1) {
            if (StringUtils.isEmpty(openid)) {
                MobclickAgent.onProfileSignIn(et_accountNumber.getText().toString());
                CrashReport.putUserData(this, "mobile", et_accountNumber.getText().toString());
            } else {
                MobclickAgent.onProfileSignIn(openid);
                CrashReport.putUserData(this, "openid", openid);
            }
            dismissLoadingDialog();
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusLoginEvent"));
            finish();
        } else if (flag == 2) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            if (bean.getData().getResultX().contains("false")) {
                errorMsg("4000", 0);
                return;
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "face", bean.getData().getFace());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "username", bean.getData().getUsername());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "rongYunToken", bean.getData().getRong_cloud());
            ((LoginContract.Presenter) mPresenter).loginRongYun(bean.getData().getRong_cloud(), bean);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg.equals("4000")) {
            Intent intent = new Intent(aty, BindingPhoneActivity.class);
            intent.putExtra("openid", openid);
            intent.putExtra("from", from);
            intent.putExtra("nickname", nickname);
            intent.putExtra("head_pic", head_pic);
            intent.putExtra("sex", sex);
            showActivity(aty, intent);
            return;
        }
        UserUtil.clearUserInfo(this);
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    public void changeInputView(final EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 0) {
                    if (view != null) {
                        view.setVisibility(View.VISIBLE);
                    }
                    if (et_accountNumber.getText().length() > 0 && et_pwd.getText().length() > 0) {
                        tv_login.setClickable(true);
                        tv_login.setBackgroundResource(R.drawable.shape_login1);
                        tv_login.setTextColor(getResources().getColor(android.R.color.white));
                    } else {
                        tv_login.setClickable(false);
                        tv_login.setBackgroundResource(R.drawable.shape_login);
                        tv_login.setTextColor(getResources().getColor(android.R.color.white));
                    }
                } else {
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    tv_login.setClickable(false);
                    tv_login.setBackgroundResource(R.drawable.shape_login);
                    tv_login.setTextColor(getResources().getColor(android.R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 微信QQ， 登录
     * 第三方授权
     */
    private void thirdLogin(SHARE_MEDIA platform) {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(LoginActivity.this).setShareConfig(config);
        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, platform, umAuthListener);
    }


    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            showLoadingDialog(getString(R.string.authorizationLoad));
        }

        /**
         * @desc 授权成功的回调
         * @param share_media 平台名称
         * @param action 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA share_media, int action, Map<String, String> map) {
            showLoadingDialog(getString(R.string.loggingLoad));
            String temp = "";
            //    Toast.makeText(LoginActivity.this, new Gson().toJson(map), Toast.LENGTH_LONG).show(); ouCjs1GFLA4yOb44uCKwIb9-d6Cw
            for (String key : map.keySet()) {
                temp = temp + key + " : " + map.get(key) + "\n";
            }
            Log.d("tag111", temp);
            if (map.get("gender") != null && map.get("gender").contains(getString(R.string.man))) {
                sex = 1;
            } else if (map.get("gender") != null && map.get("gender").contains(getString(R.string.woman))) {
                sex = 2;
            } else {
                sex = 0;
            }
            openid = map.get("uid");
            //  openid = map.get("openid");
            Log.d("tag111", openid);
            from = share_media.toString();
            if (from != null && from.equals("WEIXIN")) {
                from = "weixin";
            } else {
                from = "qq";
            }
            nickname = map.get("name");
            head_pic = map.get("iconurl");
            ((LoginContract.Presenter) mPresenter).postThirdToLogin(openid, from, nickname, head_pic, sex);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
                ViewInject.toast(getString(R.string.authoriseErr2));
                return;
            }
            ViewInject.toast(getString(R.string.authoriseErr));
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.authoriseErr1));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
