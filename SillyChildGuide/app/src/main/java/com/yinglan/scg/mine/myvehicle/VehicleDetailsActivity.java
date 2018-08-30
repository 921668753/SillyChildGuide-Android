package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scg.R;

/**
 * 车辆详情
 */
public class VehicleDetailsActivity extends BaseActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicledetails);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vehicleDetails), true, R.id.titlebar);
    }
}
