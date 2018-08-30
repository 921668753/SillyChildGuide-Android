package com.common.cklibrary.utils;

import android.content.Context;
import android.widget.ImageView;


import com.common.cklibrary.R;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate;

/**
 * 刷新控件
 * Created by Administrator on 2017/6/7.
 */

public class RefreshLayoutUtil {


    /**
     * 设置刷新控件
     */
    @SuppressWarnings("deprecation")
    public static void initRefreshLayout(BGARefreshLayout mRefreshLayout, BGARefreshLayoutDelegate delegate, Context context, boolean isLoadingMoreEnabled) {
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(delegate);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(context, isLoadingMoreEnabled);
        ImageView mHeaderArrowIv = (ImageView) refreshViewHolder.getRefreshHeaderView().findViewById(cn.bingoogolapple.refreshlayout.R.id.iv_normal_refresh_header_arrow);
        mHeaderArrowIv.setImageResource(R.drawable.pull_icon_big);
        refreshViewHolder.setPullDownRefreshText(context.getString(R.string.pullDownRefreshText));//继续往下拉的文本
        refreshViewHolder.setRefreshingText(context.getString(R.string.refreshingText));//正在刷新时的文本
        refreshViewHolder.setReleaseRefreshText(context.getString(R.string.releaseRefreshText));//刷新条件时的文本
//        TextView mHeaderStatusTv = (TextView) refreshViewHolder.getRefreshHeaderView().findViewById(cn.bingoogolapple.refreshlayout.R.id.tv_normal_refresh_header_status);
//        mHeaderStatusTv.setTextColor(getResources().getColor(R.color.whiteColor));
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }


}
