package com.yinglan.scg.adapter.orderreceiving.dialog;

import android.content.Context;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.orderreceiving.dialog.UnwillingnessTakeOrdersBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


public class UnwillingnessTakeOrdersViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public UnwillingnessTakeOrdersViewAdapter(Context context) {
        super(context, R.layout.item_reason);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_reason, listBean.getReason());


    }
}
