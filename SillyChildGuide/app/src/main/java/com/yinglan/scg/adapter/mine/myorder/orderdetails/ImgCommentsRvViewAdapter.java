package com.yinglan.scg.adapter.mine.myorder.orderdetails;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.R;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 图片显示
 */
public class ImgCommentsRvViewAdapter extends BGARecyclerViewAdapter<LocalMedia> {

    public ImgCommentsRvViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_chooseimage);
    }

//    public ImgCommentsRvViewAdapter(Context context) {
//        super(context, R.layout.item_chooseimage);
//    }

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
