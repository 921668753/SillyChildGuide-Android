package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scg.R;


/**
 * 新增车辆
 */
public class AddVehicleActivity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addvehicle);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addVehicle), true, R.id.titlebar);
    }
}
