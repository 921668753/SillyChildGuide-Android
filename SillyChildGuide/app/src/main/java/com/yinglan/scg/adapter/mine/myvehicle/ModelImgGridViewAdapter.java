package com.yinglan.scg.adapter.mine.myvehicle;

import android.content.Context;
import android.view.View;

import com.yinglan.scg.R;
import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 车型图片---GridView
 */
public class ModelImgGridViewAdapter extends BGAAdapterViewAdapter<LocalMedia> {

    public ModelImgGridViewAdapter(Context context) {
        super(context, R.layout.item_chooseimage);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, LocalMedia model) {


        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getPath(), helper.getImageView(R.id.iv_img), R.mipmap.placeholderfigure);


        /**
         * 机场名字
         */
        helper.setVisibility(R.id.iv_delete, View.GONE);

    }
}
