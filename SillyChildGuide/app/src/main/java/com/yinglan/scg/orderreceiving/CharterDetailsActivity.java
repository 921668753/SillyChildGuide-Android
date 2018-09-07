package com.yinglan.scg.orderreceiving;

import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scg.R;

/**
 * 包车订单详情
 */
public class CharterDetailsActivity extends BaseActivity {

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_orderPrice)
    private TextView tv_orderPrice;

    @BindView(id = R.id.tv_demand)
    private TextView tv_demand;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_charterdetails);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, "", true, R.id.titlebar);
    }
}
