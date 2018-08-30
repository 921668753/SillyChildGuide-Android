package com.yinglan.scg.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕尺寸的单位转换
 * Created by Administrator on 2017/11/29.
 */

public class DisplayUtil {
    public static int dip2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据屏幕的尺寸，获取适配所用的宽度,返回值的单位是px
     *
     * @param context
     * @param riddp       需要去除的间距，单位dp
     * @param numerator   分子     和denominator可同时为零，
     * @param denominator 分母     为零时，numerator和denominator无效
     * @return
     */
    public static int getWidthForScreen(Context context, int riddp, float numerator, float denominator) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (denominator > 0) {
            return (int) ((displayMetrics.widthPixels - dip2px(context, riddp)) / denominator * numerator);
        } else {
            return displayMetrics.widthPixels - dip2px(context, riddp);
        }
    }

}
