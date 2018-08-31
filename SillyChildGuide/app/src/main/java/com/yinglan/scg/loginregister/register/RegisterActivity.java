package com.yinglan.scg.loginregister.register;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.PreferenceHelper;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.loginregister.LoginBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.mine.setup.HelpCenterActivity;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
import static com.yinglan.scg.constant.URLConstants.REGISTPROTOOL;

/**
 * 手机号注册
 * Created by Admin on 2017/8/10.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;


    /**
     * 倒计时内部类
     */
    private TimeCount time;


    /**
     * 手机号
     */
    @BindView(id = R.id.et_accountNumber)
    private EditText et_accountNumber;

    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;

    @BindView(id = R.id.img_yanjing, click = true)
    private ImageView img_yanjing;

    /**
     * 验证码
     */
    @BindView(id = R.id.et_code)
    private EditText et_code;
    /**
     * 获取验证码
     */
    @BindView(id = R.id.tv_code, click = true)
    private TextView tv_code;

    /**
     * 注册
     */
    @BindView(id = R.id.tv_registe, click = true)
    private TextView tv_registe;

    /**
     * 注册协议
     */
    @BindView(id = R.id.tv_agreement, click = true)
    private TextView tv_agreement;


    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String opt = "reg";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new RegisterPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_code:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((RegisterContract.Presenter) mPresenter).postCode(et_accountNumber.getText().toString(), opt);
                break;
            case R.id.tv_registe:
                tv_registe.setEnabled(false);
                showLoadingDialog(getString(R.string.submissionLoad));
                if (opt.equals("reg")) {
                    ((RegisterContract.Presenter) mPresenter).postRegister(et_accountNumber.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString());
                }
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
            case R.id.tv_agreement:
                // 注册协议
                Intent intent = new Intent(aty, HelpCenterActivity.class);
                intent.putExtra("title", getString(R.string.agreement1));
                intent.putExtra("url", REGISTPROTOOL);
                showActivity(aty, intent);
                break;
            default:
                break;
        }


    }

    /* 定义一个倒计时的内部类 */
    @SuppressWarnings("deprecation")
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_code.setText(getString(R.string.revalidation));
            tv_code.setClickable(true);
            tv_code.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_code.setBackgroundResource(R.drawable.shape_code);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished / 1000 + getString(R.string.toResend));
            tv_code.setTextColor(getResources().getColor(R.color.hintColors));
            tv_code.setBackgroundResource(R.drawable.shape_code1);
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        tv_registe.setEnabled(true);
        if (flag == 0) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", et_accountNumber.getText().toString());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "face", bean.getData().getFace());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "username", bean.getData().getUsername());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "rongYunToken", bean.getData().getRong_cloud());
            ((RegisterContract.Presenter) mPresenter).loginRongYun(bean.getData().getRong_cloud(), bean);
        } else if (flag == 2) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusLoginEvent"));
            MobclickAgent.onProfileSignIn(et_accountNumber.getText().toString());
            CrashReport.putUserData(this, "mobile", et_accountNumber.getText().toString());
            dismissLoadingDialog();
            KJActivityStack.create().finishActivity(LoginActivity.class);
            aty.finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        aty.runOnUiThread(new Runnable() {
            public void run() {
                ViewInject.toast(msg);
            }
        });
        if (flag == 1) {
            UserUtil.clearUserInfo(this);
            aty.finish();
        }
        tv_registe.setEnabled(true);
    }


    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
        time = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {// 如果等于1
            // 说明是我们的那次请求
            // 目的：区分请求，不同的请求要做不同的处理
//            countroy_code = data.getStringExtra("areaCode");
//            tv_areaCode.setText("+" + countroy_code);
        }
    }
}
