package com.yinglan.scg.mine.mywallet.mybankcard;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.mywallet.mybankcard.AddBankCardBean;
import com.yinglan.scg.entity.mine.mywallet.mybankcard.BankBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.utils.SoftKeyboardUtils;

import java.util.List;

/**
 * 添加银行卡
 * Created by Administrator on 2017/11/30.
 */

public class AddBankCardActivity extends BaseActivity implements AddBankCardContract.View {

    /**
     * 持卡人
     */
    @BindView(id = R.id.et_cardholder)
    private EditText et_cardholder;

    /**
     * 身份证号
     */
    @BindView(id = R.id.et_idNumber)
    private EditText et_idNumber;

    /**
     * 银行卡号
     */
    @BindView(id = R.id.et_bankCardNumber)
    private EditText et_bankCardNumber;

    /**
     * 开户银行
     */
    @BindView(id = R.id.ll_openingBank, click = true)
    private LinearLayout ll_openingBank;
    @BindView(id = R.id.tv_openingBank)
    private TextView tv_openingBank;

    /**
     * 手机号
     */
    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    /**
     * 倒计时内部类
     */
    private TimeCount time;

    /**
     * t验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String type = "reg";

    /**
     * 验证码
     */
    @BindView(id = R.id.et_verificationCode)
    private EditText et_verificationCode;

    @BindView(id = R.id.tv_verificationCode, click = true)
    private TextView tv_verificationCode;

    /**
     * 确认添加
     */
    @BindView(id = R.id.tv_prepaidImmediately, click = true)
    private TextView tv_prepaidImmediately;

    private OptionsPickerView pvOptions;

    private List<BankBean.DataBean> bankList;


    private int bankCardId = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addbankcard);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddBankCardPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        selectBankName();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AddBankCardContract.Presenter) mPresenter).getBank();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addBankCard), true, R.id.titlebar);
        changeInputView(et_phone);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_openingBank:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_openingBank);
                break;
            case R.id.tv_verificationCode:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((AddBankCardContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), type);
                break;
            case R.id.tv_prepaidImmediately:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((AddBankCardContract.Presenter) mPresenter).postAddBankCard(et_cardholder.getText().toString().trim(), et_idNumber.getText().toString().trim(),
                        tv_openingBank.getText().toString(), et_bankCardNumber.getText().toString().trim(), et_phone.getText().toString().trim(),
                        et_verificationCode.getText().toString().trim());
                break;
        }
    }


    /**
     * 选择银行名称
     */
    @SuppressWarnings("unchecked")
    private void selectBankName() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  bankName = bankList.get(options1).getId();
                ((TextView) v).setText(bankList.get(options1).getName());
            }
        }).build();
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_verificationCode.setText(getString(R.string.revalidation));
            tv_verificationCode.setClickable(true);
            tv_verificationCode.setBackgroundResource(R.drawable.shape_login1);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_verificationCode.setClickable(false);
            tv_verificationCode.setText(millisUntilFinished / 1000 + getString(R.string.toResend));
            tv_verificationCode.setBackgroundResource(R.drawable.shape_login);
        }
    }


    @Override
    public void setPresenter(AddBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            BankBean bankBean = (BankBean) JsonUtil.json2Obj(success, BankBean.class);
            bankList = bankBean.getData();
            if (bankList != null && bankList.size() > 0) {
                pvOptions.setPicker(bankList);
            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            AddBankCardBean addBankCardBean = (AddBankCardBean) JsonUtil.json2Obj(success, AddBankCardBean.class);
            bankCardId = addBankCardBean.getData().getId();
            getSuccess("", 3);
            // ((AddBankCardContract.Presenter) mPresenter).postPurseDefault(bankCardId);
        } else if (flag == 3) {
            dismissLoadingDialog();
            Intent intent = getIntent();
            //设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    public void changeInputView(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() == 11) {
                    tv_verificationCode.setClickable(true);
                    tv_verificationCode.setBackgroundResource(R.drawable.shape_login1);
                } else {
                    tv_verificationCode.setClickable(false);
                    tv_verificationCode.setBackgroundResource(R.drawable.shape_login);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvOptions = null;
    }
}
