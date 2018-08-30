package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scg.R;

/**
 * 我的车辆
 */
public class MyVehicleActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myvehicle);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myVehicle), true, R.id.titlebar);
    }
}
