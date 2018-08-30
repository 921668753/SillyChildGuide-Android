package com.common.cklibrary.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by crobot on 2016/6/12.
 */

public  class MathUtil {
    public static String keepTwo(double originalNumber){
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
        return df.format(originalNumber);
    }

    public static String keepZero(double originalNumber){
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0");
        return df.format(originalNumber);
    }

    /**
     * 判断非负数的整数或者携带一位或者两位的小数
     *
     * @param obj
     * @return boolean
     * @throws
     * @function:
     * @author:
     * @since 1.0.0
     */
    public static boolean judgeTwoDecimal(Object obj) {
        boolean flag = false;
        try {
            if (obj != null) {
                String source = obj.toString();
                // 判断是否是整数或者是携带一位或者两位的小数
                Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
                if (pattern.matcher(source).matches()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 两个字符串进行减法运算，并保留相应小数位
     * @param str1
     * @param str2
     * @param decimalnum
     * @return
     */
    public static String subtractWithDecimal(String str1,String str2,int decimalnum){
        BigDecimal bigDecimal1=new BigDecimal(str1);
        BigDecimal bigDecimal2=new BigDecimal(str2);
        BigDecimal bigDecimal3=bigDecimal1.subtract(bigDecimal2);
        bigDecimal3=bigDecimal3.setScale(decimalnum,BigDecimal.ROUND_HALF_DOWN);
        return bigDecimal3.toString();
    }
}
