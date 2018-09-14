package com.yinglan.scg.adapter.service;

import android.content.Context;

import com.common.cklibrary.utils.DataUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.service.dialog.TripBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


public class TripViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public TripViewAdapter(Context context) {
        super(context, R.layout.item_trip);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean model) {

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, model.getProduct_set_name());

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_content, model.getTitle());
        /**
         * 标题
         */
        switch (model.getProduct_set_cd()) {
            case 1://接机
            case 2://送机
                viewHolderHelper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd E HH:mm"));
                break;
            case 3://包车
                viewHolderHelper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd E"));
                break;
            case 4://私人定制
            case 5://线路
                viewHolderHelper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getStart_time()), "yyyy-MM-dd") + "—" + DataUtil.formatData(StringUtils.toLong(model.getEnd_time()), "yyyy-MM-dd"));
                break;
            default:
                break;
        }
    }
}
