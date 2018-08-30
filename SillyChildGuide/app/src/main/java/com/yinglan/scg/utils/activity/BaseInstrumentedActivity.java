package com.yinglan.scg.utils.activity;

import com.common.cklibrary.common.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送专用
 * Created by ruitu on 2016/11/4.
 */

public abstract class BaseInstrumentedActivity extends BaseActivity {
    public BaseInstrumentedActivity() {
    }

    public void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void onStop() {
        super.onStop();
    }
}
