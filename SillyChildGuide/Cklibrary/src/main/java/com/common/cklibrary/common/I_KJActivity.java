package com.common.cklibrary.common;

import android.view.View;

/**
 * KJFrameActivity接口协议，实现此接口可使用KJActivityManager堆栈<br>
 * <b>创建时间</b> 2014-3-1 <br>
 * <b>最后修改时间</b>2017/3/7.
 *
 * @author kymjs (http://www.kymjs.com)
 * @version 2.25
 */
public interface I_KJActivity {

    int DESTROY = 0;
    int STOP = 2;
    int PAUSE = 1;
    int RESUME = 3;

    /**
     * 设置root界面
     */
    void setRootView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 在线程中初始化数据
     */
    void initDataFromThread();

    /**
     * 初始化控件
     */
    void initWidget();

    /**
     * 点击事件回调方法
     */
    void widgetClick(View v);
}
