package com.yinglan.scg.adapter.mine.personaldata;

import android.content.Context;
import android.view.View;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaByCountryIdBean.DataBean;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 *  服务地区分类---GridView
 */
public class ServiceAreaClassificationGridViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public ServiceAreaClassificationGridViewAdapter(Context context) {
        super(context, R.layout.item_city);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {


        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getPicture(), helper.getImageView(R.id.img_classification), R.mipmap.placeholderfigure);


        /**
         * 城市名字
         */
        helper.setText(R.id.tv_classificationName, model.getRegion_name());

        /**
         * 机场名字
         */
        helper.setVisibility(R.id.tv_airportName, View.GONE);
        // helper.setText(R.id.tv_airportName, "(" + model.getAirport_name() + ")");
    }
}
