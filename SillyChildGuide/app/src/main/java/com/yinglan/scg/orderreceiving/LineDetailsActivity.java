package com.yinglan.scg.orderreceiving;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scg.R;

/**
 * 线路订单详情
 */
public class LineDetailsActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_linedetails);
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
