package com.yinglan.scg.adapter.mine.personaldata;

import android.content.Context;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaCountryListBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 服务地区分类---ListView
 */
public class ServiceAreaClassificationListViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public ServiceAreaClassificationListViewAdapter(Context context) {
        super(context, R.layout.item_countries);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 背景色
         */
        if (model.getIsSelected() == 0) {
            helper.setBackgroundRes(R.id.tv_country, R.color.whiteColors);
            helper.setTextColorRes(R.id.tv_country, R.color.tabColors);
        } else {
            helper.setBackgroundRes(R.id.tv_country, R.drawable.shape_country);
            helper.setTextColorRes(R.id.tv_country, R.color.whiteColors);
        }

        /**
         * 名字
         */
        helper.setText(R.id.tv_country, model.getCountry_name());
    }
}
