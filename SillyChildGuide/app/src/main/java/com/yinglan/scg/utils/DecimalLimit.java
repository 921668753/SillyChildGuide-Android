package com.yinglan.scg.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * Created by Administrator on 2017/9/19.
 */

public class DecimalLimit {

    public DecimalLimit(){

    }

    public InputFilter[] getFilter(int limitnum){

        InputFilter lengthFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // source:当前输入的字符
                // start:输入字符的开始位置
                // end:输入字符的结束位置
                // dest：当前已显示的内容
                // dstart:当前光标开始位置
                // dent:当前光标结束位置
                Log.i("", "source=" + source + ",start=" + start + ",end=" + end
                        + ",dest=" + dest.toString() + ",dstart=" + dstart
                        + ",dend=" + dend);
                if (dest.length() == 0 && source.equals(".")) {
                    return "0.";
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    if (dotValue.length() == limitnum) {
                        return "";
                    }
                }
                return null;
            }

        };
        InputFilter[] lengthFilterarry=new InputFilter[]{lengthFilter};
        return lengthFilterarry;
    }

}
