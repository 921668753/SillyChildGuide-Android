package com.yinglan.scg.adapter.orderreceiving.dialog;

import android.content.Context;
import android.view.View;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean.DataBean.ModelListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

public class SelectVehicleViewAdapter extends BGAAdapterViewAdapter<ModelListBean> {

    public SelectVehicleViewAdapter(Context context) {
        super(context, R.layout.item_myvehicle);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ModelListBean listBean) {

        /**
         * 未读消息数
         */
        if (listBean.getIs_default() == 1) {
            viewHolderHelper.setVisibility(R.id.img_button, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.img_button, View.INVISIBLE);
        }

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_carBrand, listBean.getModel_name());


    }

}