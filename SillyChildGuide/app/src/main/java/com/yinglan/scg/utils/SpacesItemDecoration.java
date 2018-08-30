package com.yinglan.scg.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 瀑布流数据间距
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int topSpace;
    private int liftSpace;

    public SpacesItemDecoration(int liftSpace, int topSpace) {
        this.liftSpace = liftSpace;
        this.topSpace = topSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = liftSpace;
        outRect.right = liftSpace;
        outRect.bottom = topSpace;
        outRect.top = topSpace;
    }
}