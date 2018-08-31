package com.yinglan.scg.loginregister.forgotpassword;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.yinglan.scg.R;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;

/**
 * 找回密码
 * Created by Admin on 2017/8/10.
 */

public class RetrievePasswordActivity extends BaseActivity implements RetrievePasswordContract.View {


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
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd1)
    private EditText et_pwd1;
    /**
     * 确定
     */
    @BindView(id = R.id.tv_determine, click = true)
    private TextView tv_determine;

    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String opt = "resetpwd";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_retrievepassword);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new RetrievePasswordPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_code:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((RetrievePasswordContract.Presenter) mPresenter).postCode(et_accountNumber.getText().toString(), opt);
                break;
            case R.id.tv_determine:
                tv_determine.setEnabled(false);
                showLoadingDialog(getString(R.string.submissionLoad));
                if (opt.equals("resetpwd")) {
                    ((RetrievePasswordContract.Presenter) mPresenter).postResetpwd(et_accountNumber.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString(), et_pwd1.getText().toString());
                }
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
        dismissLoadingDialog();
        tv_determine.setEnabled(true);
        if (flag == 0) {
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            UserUtil.clearUserInfo(this);
            ViewInject.toast(getString(R.string.resetpwd));
            aty.finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
        tv_determine.setEnabled(true);
    }


    @Override
    public void setPresenter(RetrievePasswordContract.Presenter presenter) {
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
//            areaCode = data.getStringExtra("areaCode");
//            tv_areaCode.setText("+" + areaCode);
        }

    }
}
