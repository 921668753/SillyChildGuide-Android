package com.yinglan.scg.mine.mywallet.withdrawal.withdrawalresult;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.yinglan.scg.R;
import com.yinglan.scg.mine.mywallet.withdrawal.WithdrawalActivity;

/**
 * 提现成功/提现失败
 */
public class WithdrawalCompleteActivity extends BaseActivity {


    @BindView(id = R.id.tv_withdrawalRequestSubmissionSuccess)
    private TextView tv_withdrawalRequestSubmissionSuccess;


    /**
     * 预计到账时间
     */
    @BindView(id = R.id.tv_estimatedTimeArrival)
    private TextView tv_estimatedTimeArrival;

    /**
     * 储蓄卡
     */
    @BindView(id = R.id.tv_cashCard)
    private TextView tv_cashCard;

    /**
     * 提现金额
     */
    @BindView(id = R.id.tv_withdrawalAmount)
    private TextView tv_withdrawalAmount;

    /**
     * 完成
     */
    @BindView(id = R.id.tv_complete, click = true)
    private TextView tv_complete;

    private String estimatedTimeArrival = "";

    private String cashCard = "";

    private String withdrawalAmount = "";
    private String get_time = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawalcomplete);
    }

    @Override
    public void initData() {
        super.initData();
        estimatedTimeArrival = getIntent().getStringExtra("estimatedTimeArrival");
        cashCard = getIntent().getStringExtra("cashCard");
        withdrawalAmount = getIntent().getStringExtra("withdrawalAmount");
        RxBus.getInstance().post(new MsgEvent<String>("RxBusWithdrawalEvent"));
        get_time = getIntent().getStringExtra("get_time");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_withdrawalRequestSubmissionSuccess.setText(getString(R.string.withdrawalRequestSubmissionSuccess1) + get_time + getString(R.string.withdrawalRequestSubmissionSuccess2));
        tv_estimatedTimeArrival.setText(estimatedTimeArrival);
        tv_cashCard.setText(cashCard);
        tv_withdrawalAmount.setText(withdrawalAmount);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawalRequestSucceeded), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_complete:
                KJActivityStack.create().finishActivity(WithdrawalActivity.class);
                finish();
                break;
        }
    }


}
