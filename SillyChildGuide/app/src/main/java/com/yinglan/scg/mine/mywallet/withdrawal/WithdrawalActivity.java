package com.yinglan.scg.mine.mywallet.withdrawal;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.mywallet.MyWalletBean;
import com.yinglan.scg.entity.mine.mywallet.withdrawal.WithdrawalBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.mywallet.mybankcard.AddBankCardActivity;
import com.yinglan.scg.mine.mywallet.mybankcard.MyBankCardActivity;
import com.yinglan.scg.mine.mywallet.withdrawal.withdrawalresult.WithdrawalCompleteActivity;

import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;
import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE;


/**
 * 提现
 * Created by Administrator on 2017/2/17.
 */

public class WithdrawalActivity extends BaseActivity implements WithdrawalContract.View {

    /**
     * 提险金额
     */
    @BindView(id = R.id.et_withdrawalAmount1)
    private EditText et_withdrawalAmount1;

    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 中国银行（尾号3215）
     */
    @BindView(id = R.id.tv_withdrawalBank)
    private TextView tv_withdrawalBank;

    /**
     * 费用
     */
    @BindView(id = R.id.tv_poundage)
    private TextView tv_poundage;

    /**
     * 选择银行卡
     */
    @BindView(id = R.id.ll_bank, click = true)
    private LinearLayout ll_bank;

    @BindView(id = R.id.tv_accountingDate)
    private TextView tv_accountingDate;


    /**
     * 确定
     */
    @BindView(id = R.id.tv_confirmSubmit, click = true)
    private TextView tv_confirmSubmit;

    private String bankCardName = "";
    private String bankCardNun = "";
    private int bankCardId = 0;
    private String fee = "";
    private String get_time = "5";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new WithdrawalPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((WithdrawalContract.Presenter) mPresenter).getMyWallet();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawal), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_bank:
                ((WithdrawalContract.Presenter) mPresenter).getIsLogin(2);
                break;
            case R.id.tv_confirmSubmit:
                ((WithdrawalContract.Presenter) mPresenter).postWithdrawal(et_withdrawalAmount1.getText().toString().trim(), bankCardId);
                break;
        }
    }


    @Override
    public void setPresenter(WithdrawalContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(success, MyWalletBean.class);
            if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "withdrawalAmount", MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
                if (!StringUtils.isEmpty(myWalletBean.getData().getOpen_bank()) && !StringUtils.isEmpty(myWalletBean.getData().getAccount_no())) {
                    bankCardName = myWalletBean.getData().getOpen_bank();
                    bankCardNun = myWalletBean.getData().getAccount_no();
                    bankCardNun = bankCardNun.substring(bankCardNun.length() - 4);
                    bankCardId = myWalletBean.getData().getBank_id();
                    fee = myWalletBean.getData().getFee() + "%";
                    get_time = myWalletBean.getData().getGet_time();
                }
                tv_money.setText(MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
                tv_accountingDate.setText(getString(R.string.accountingDate) + get_time + getString(R.string.accountingDate1));
                if (StringUtils.isEmpty(bankCardName) || StringUtils.isEmpty(bankCardNun)) {
                    tv_withdrawalBank.setText(getString(R.string.noCard));
                    tv_poundage.setVisibility(View.GONE);
                    return;
                }
                tv_poundage.setVisibility(View.VISIBLE);
                tv_withdrawalBank.setText(bankCardName + "  (" + bankCardNun + ")");
                tv_poundage.setText(getString(R.string.withdrawalTo) + bankCardName + getString(R.string.procedureRrates) + fee);
            }
        } else if (flag == 1) {
            WithdrawalBean withdrawalBean = (WithdrawalBean) JsonUtil.json2Obj(success, WithdrawalBean.class);
            Intent intent = new Intent(aty, WithdrawalCompleteActivity.class);
            intent.putExtra("estimatedTimeArrival", withdrawalBean.getData().getTime());
            intent.putExtra("cashCard", bankCardName + "  " + getString(R.string.tail) + bankCardNun);
            intent.putExtra("withdrawalAmount", getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(et_withdrawalAmount1.getText().toString().trim()) -
                    StringUtils.toDouble(withdrawalBean.getData().getFee_amount())) + getString(R.string.serviceChargeDeducted));
            intent.putExtra("get_time", get_time);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (flag == 2) {
            if (StringUtils.isEmpty(bankCardName) || StringUtils.isEmpty(bankCardNun)) {
                Intent intent = new Intent(aty, AddBankCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
                return;
            }
            Intent intent = new Intent(aty, MyBankCardActivity.class);
            intent.putExtra("type", 1);
            startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String withdrawalAmount = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdrawalAmount");
            tv_money.setText(withdrawalAmount);
            et_withdrawalAmount1.setText("");
        } else if (requestCode == REQUEST_CODE_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            ((WithdrawalContract.Presenter) mPresenter).getMyWallet();
        }
    }

}
