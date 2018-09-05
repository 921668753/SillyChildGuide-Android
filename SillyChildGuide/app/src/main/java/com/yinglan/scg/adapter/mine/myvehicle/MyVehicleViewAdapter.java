package com.yinglan.scg.adapter.mine.myvehicle;

import android.content.Context;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.myvehicle.MyVehicleBean.DataBean;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 我的车辆适配器
 */
public class MyVehicleViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyVehicleViewAdapter(Context context) {
        super(context, R.layout.item_car);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_delete);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, DataBean resultBean) {

        helper.setText(R.id.tv_auditStatus, "");

        GlideImageLoader.glideOrdinaryLoader(mContext, "", helper.getImageView(R.id.img_car), R.mipmap.placeholderfigure);

        helper.setText(R.id.tv_carBrand, "");

        helper.setText(R.id.tv_carModels, "");

        helper.setText(R.id.tv_seatNumber, "");
    }
}
