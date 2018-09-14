package com.yinglan.scg.adapter.mine.myorder;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.DataUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.myorder.ForServiceBean.DataBean.ProcessingListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

public class ForServiceProcessingViewAdapter extends BGAAdapterViewAdapter<ProcessingListBean> {

    public ForServiceProcessingViewAdapter(Context context) {
        super(context, R.layout.item_order);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ProcessingListBean model) {
        if (position == 0) {
            viewHolderHelper.setVisibility(R.id.tv_statusOrder, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_statusOrder, View.GONE);
        }
        viewHolderHelper.setVisibility(R.id.tv_divider, View.GONE);
        switch (model.getOrder_status()) {
            case 1://待服务
                viewHolderHelper.setText(R.id.tv_statusOrder, mContext.getString(R.string.pending));
                viewHolderHelper.setTextColorRes(R.id.tv_statusOrder, R.color.fF5656Colors);
                viewHolderHelper.setBackgroundRes(R.id.tv_statusOrder, R.drawable.shape_pending);
                break;
            case 2://进行中
                viewHolderHelper.setText(R.id.tv_statusOrder, mContext.getString(R.string.ongoing));
                viewHolderHelper.setTextColorRes(R.id.tv_statusOrder, R.color.greenColors);
                viewHolderHelper.setBackgroundRes(R.id.tv_statusOrder, R.drawable.shape_airporttransportationorder);
                break;
            case 3://待评价
                viewHolderHelper.setText(R.id.tv_statusOrder, mContext.getString(R.string.toEvaluate));
                viewHolderHelper.setTextColorRes(R.id.tv_statusOrder, R.color.ff9955Colors);
                viewHolderHelper.setBackgroundRes(R.id.tv_statusOrder, R.drawable.shape_toevaluate);
                break;
            case 4://完成
                viewHolderHelper.setText(R.id.tv_statusOrder, mContext.getString(R.string.completed));
                viewHolderHelper.setTextColorRes(R.id.tv_statusOrder, R.color.hintColors);
                viewHolderHelper.setBackgroundRes(R.id.tv_statusOrder, R.drawable.shape_completed);
                break;
            default:
                break;
        }
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