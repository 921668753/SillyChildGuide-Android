package com.common.cklibrary.utils.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyNoScrollGridView extends GridView {

    public MyNoScrollGridView(Context context) {
        super(context);
    }

    public MyNoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNoScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}