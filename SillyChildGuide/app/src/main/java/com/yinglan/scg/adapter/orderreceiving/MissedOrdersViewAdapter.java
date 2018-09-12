package com.yinglan.scg.adapter.orderreceiving;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.DataUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.main.OrderReceivingBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

public class MissedOrdersViewAdapter extends BGAAdapterViewAdapter<OrderReceivingBean.DataBean.ResultBean> {

    public MissedOrdersViewAdapter(Context context) {
        super(context, R.layout.item_order);
    }

//    public OrderReceivingViewAdapter(RecyclerView recyclerView) {
//        super(recyclerView);
//    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
//        super.setItemChildListener(helper, viewType);
////        helper.setItemChildClickListener(R.id.tv_confirmPayment);
////        helper.setItemChildClickListener(R.id.tv_callUp);
////        helper.setItemChildClickListener(R.id.tv_sendPrivateChat);
////        helper.setItemChildClickListener(R.id.tv_appraiseOrder);
////        helper.setItemChildClickListener(R.id.tv_additionalComments);
//    }


    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, OrderReceivingBean.DataBean.ResultBean model) {
        viewHolderHelper.setVisibility(R.id.tv_statusOrder, View.GONE);
        viewHolderHelper.setVisibility(R.id.tv_divider, View.GONE);
        switch (model.getProduct_set_cd()) {
            case 1://接机
            case 2://送机
                viewHolderHelper.setVisibility(R.id.ll_air, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.ll_charter, View.GONE);
                viewHolderHelper.setText(R.id.tv_name, model.getProduct_set_name());
                viewHolderHelper.setText(R.id.tv_startLocation, model.getOrigin_name());
                viewHolderHelper.setText(R.id.tv_endLocation, model.getDestination_name());
                viewHolderHelper.setText(R.id.tv_serviceTime, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd E HH:mm"));
                viewHolderHelper.setText(R.id.tv_orderAmount, mContext.getString(R.string.rmb) + "  " + model.getOrder_price());
                break;
            case 3://包车
                viewHolderHelper.setVisibility(R.id.ll_air, View.GONE);
                viewHolderHelper.setVisibility(R.id.ll_charter, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_name1, model.getProduct_set_name());
                viewHolderHelper.setText(R.id.tv_title, model.getProduct_name());
                viewHolderHelper.setText(R.id.tv_orderAmount1, mContext.getString(R.string.rmb) + "  " + model.getOrder_price());
                viewHolderHelper.setText(R.id.tv_serviceTime1, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd E"));
                break;
            case 4://私人定制
            case 5://线路
                viewHolderHelper.setVisibility(R.id.ll_air, View.GONE);
                viewHolderHelper.setVisibility(R.id.ll_charter, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_name1, model.getProduct_set_name());
                viewHolderHelper.setText(R.id.tv_title, model.getProduct_name());
                viewHolderHelper.setText(R.id.tv_orderAmount1, mContext.getString(R.string.rmb) + "  " + model.getOrder_price());
                viewHolderHelper.setText(R.id.tv_serviceTime1, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd") + "—" + DataUtil.formatData(StringUtils.toLong(model.getEnd_time()), "yyyy-MM-dd"));
                break;
            default:
                break;
        }

    }
}


