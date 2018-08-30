package com.common.cklibrary.utils;

import android.os.CountDownTimer;

/* *
 *  定义一个倒计时的内部类
 */
public class TimeCount extends CountDownTimer {

    private long millisInFuture = 0;
    private long countDownInterval = 0;
    private TimeCountCallBack callBack;//回调

    public TimeCount() {
        super(0, 0);
    }

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    public void setMillisCountDown(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
    }


    @Override
    public void onFinish() {// 计时完毕时触发
        callBack.onFinishTime();
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        callBack.onTick(millisUntilFinished);
    }

    public void setTimeCountCallBack(TimeCountCallBack callBack) {
        this.callBack = callBack;
    }

    public interface TimeCountCallBack {
        void onFinishTime();

        void onTick(long millisUntilFinished);
    }

}
