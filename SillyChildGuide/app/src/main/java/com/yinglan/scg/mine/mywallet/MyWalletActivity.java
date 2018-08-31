package com.yinglan.scg.mine.mywallet;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.mywallet.MyWalletBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.mywallet.accountdetails.AccountDetailsActivity;
import com.yinglan.scg.mine.mywallet.mybankcard.MyBankCardActivity;
import com.yinglan.scg.mine.mywallet.withdrawal.WithdrawalActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.tv_yue)
    private TextView tv_yue;

    @BindView(id = R.id.ll_withdraw, click = true)
    private LinearLayout ll_withdraw;

    @BindView(id = R.id.ll_bankCard, click = true)
    private LinearLayout ll_bankCard;


//    private String bankCardName;
//    private String bankCardNun;
//    private String fee;
//    private int bankCardId = 0;
//    private String get_time;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyWalletContract.Presenter) mPresenter).getMyWallet();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setBackgroundColor(Color.TRANSPARENT);
        titlebar.setLeftDrawable(getResources().getDrawable(R.mipmap.loginback));
        titlebar.getTitleCtv().setTextColor(getResources().getColor(R.color.whiteColors));
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.whiteColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickRightCtv() {
                showActivity(aty, AccountDetailsActivity.class);
            }

            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                finish();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), getString(R.string.accountDetails), R.id.titlebar, simpleDelegate);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_withdraw:
                showActivity(aty, WithdrawalActivity.class);
                break;
            case R.id.ll_bankCard:
                showActivity(aty, MyBankCardActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(success, MyWalletBean.class);
            if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
                tv_yue.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
                PreferenceHelper.write(this, StringConstants.FILENAME, "withdrawalAmount", MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
//                if (!StringUtils.isEmpty(myWalletBean.getData().getOpen_bank()) && !StringUtils.isEmpty(myWalletBean.getData().getAccount_no())) {
//                    bankCardName = myWalletBean.getData().getOpen_bank();
//                    bankCardNun = myWalletBean.getData().getAccount_no();
//                    bankCardNun = bankCardNun.substring(bankCardNun.length() - 4);
//                    bankCardId = myWalletBean.getData().getBank_id();
//                    fee = myWalletBean.getData().getFee() + "%";
//                    get_time = myWalletBean.getData().getGet_time();
//                }
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            finish();
        } else {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
    }

    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusWithdrawalEvent")) {
            ((MyWalletContract.Presenter) mPresenter).getMyWallet();
        }
    }


}
