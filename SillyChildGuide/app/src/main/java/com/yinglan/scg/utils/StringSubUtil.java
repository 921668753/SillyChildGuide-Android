package com.yinglan.scg.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/10/23.
 */

public class StringSubUtil {

    public static String getStringNoNumber(String original) {
        boolean firstisnonum = false;
        int firstnonum = 0;
        boolean firstisnum = false;
        int firstnum = 0;
        if (original == null || TextUtils.isEmpty(original)) {
            return "";
        } else {
            for (int i = 0; i < original.length(); i++) {
                if (original.charAt(i) == '.' || original.charAt(i) == ',') {
                    continue;
                } else if (original.charAt(i) < 48 || original.charAt(i) > 57) {//非数字
                    if (!firstisnonum && i != 0) {
                        firstnonum = i;
                        break;
                    } else if (!firstisnonum) {
                        firstisnonum = true;
                        firstnonum = i;
                    }
                } else if (original.charAt(i) >= 48 && original.charAt(i) <= 57) {//数字
                    if (firstisnonum) {
                        firstnum = i;
                        break;
                    }
                }
            }
            if (firstnonum == 0) {
                return original.substring(0, firstnum);
            } else {
                return original.substring(firstnonum);
            }
        }
    }

}
