package com.yinglan.scg.adapter.mine.myvehicle;

import android.content.Context;
import android.widget.ImageView;

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
        switch (resultBean.getModel_status()) {
            case 0:
                helper.setText(R.id.tv_auditStatus, mContext.getString(R.string.unauthorized));
                break;
            case 1:
                helper.setText(R.id.tv_auditStatus, mContext.getString(R.string.audit2));
                break;
            case 2:
                helper.setText(R.id.tv_auditStatus, mContext.getString(R.string.unapprove));
                break;
            case 3:
                helper.setText(R.id.tv_auditStatus, mContext.getString(R.string.approve));
                break;
        }
        ImageView img_car = helper.getImageView(R.id.img_car);
        GlideImageLoader.glideLoaderRaudio(mContext, resultBean.getModel_picture() + "?imageView2/0/w/" + img_car.getWidth() + "/h/" + img_car.getHeight(), img_car, 8, R.mipmap.placeholderfigure);

        if (resultBean.getIs_default() == 1) {
            helper.setText(R.id.tv_delete, mContext.getString(R.string.defaultVehicle));
            helper.setBackgroundRes(R.id.tv_delete, R.drawable.shape_delete);
            helper.setTextColorRes(R.id.tv_delete, R.color.whiteColors);
        } else {
            helper.setText(R.id.tv_delete, mContext.getString(R.string.setDefault));
            helper.setBackgroundRes(R.id.tv_delete, R.drawable.shape_delete1);
            helper.setTextColorRes(R.id.tv_delete, R.color.fF5656Colors);
        }

        helper.setText(R.id.tv_carBrand, resultBean.getModel_brand());

        helper.setText(R.id.tv_carModels, resultBean.getModel_name());

        helper.setText(R.id.tv_seatNumber, mContext.getString(R.string.canTakeNumber) + resultBean.getPassenger_number() + mContext.getString(R.string.people));
    }
}
