package com.yinglan.scg.adapter.service;

import android.content.Context;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.orderreceiving.dialog.UnwillingnessTakeOrdersBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


public class TripViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public TripViewAdapter(Context context) {
        super(context, R.layout.item_trip);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getReason());

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_content, listBean.getReason());
        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getReason());
    }
}
