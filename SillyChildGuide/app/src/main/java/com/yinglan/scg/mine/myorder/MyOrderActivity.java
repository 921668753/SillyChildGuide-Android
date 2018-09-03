package com.yinglan.scg.mine.myorder;

import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.Log;
import com.yinglan.scg.R;

/**
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {

    @BindView(id = R.id.ll_forService, click = true)
    private LinearLayout ll_forService;

    @BindView(id = R.id.tv_forService)
    private TextView tv_forService;

    @BindView(id = R.id.tv_forService1)
    private TextView tv_forService1;

    @BindView(id = R.id.ll_toEvaluate, click = true)
    private LinearLayout ll_toEvaluate;

    @BindView(id = R.id.tv_toEvaluate)
    private TextView tv_toEvaluate;

    @BindView(id = R.id.tv_toEvaluate1)
    private TextView tv_toEvaluate1;

    @BindView(id = R.id.ll_completed, click = true)
    private LinearLayout ll_completed;

    @BindView(id = R.id.tv_completed)
    private TextView tv_completed;

    @BindView(id = R.id.tv_completed1)
    private TextView tv_completed1;

    @BindView(id = R.id.ll_all, click = true)
    private LinearLayout ll_all;

    @BindView(id = R.id.tv_all)
    private TextView tv_all;

    @BindView(id = R.id.tv_all1)
    private TextView tv_all1;

    private int chageIcon = 0;
    private BaseFragment baseFragment;
    private BaseFragment baseFragment1;
    private BaseFragment baseFragment2;
    private BaseFragment baseFragment3;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myorder);
    }

    @Override
    public void initData() {
        super.initData();
        baseFragment = new ForServiceFragment();
        baseFragment1 = new ToEvaluateFragment();
        baseFragment2 = new CompletedFragment();
        baseFragment3 = new AllFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (chageIcon == 0) {
            chageIcon = 0;
            cleanColors(chageIcon);
        } else if (chageIcon == 1) {
            chageIcon = 1;
            cleanColors(chageIcon);
        } else {
            chageIcon = 0;
            cleanColors(chageIcon);
        }

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myOrder), true, R.id.titlebar);
    }

    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 0);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(ll_forService, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_toEvaluate, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(ll_completed, 160, 100);
        } else if (newChageIcon == 3) {
            setSimulateClick(ll_all, 160, 100);
        } else {
            setSimulateClick(ll_forService, 160, 100);
        }
    }

    /**
     * 模拟点击
     *
     * @param view
     * @param x
     * @param y
     */
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.order_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_forService:
                chageIcon = 0;
                cleanColors(chageIcon);
                break;
            case R.id.ll_toEvaluate:
                chageIcon = 1;
                cleanColors(chageIcon);
                break;
            case R.id.ll_completed:
                chageIcon = 2;
                cleanColors(chageIcon);
                break;
            case R.id.ll_all:
                chageIcon = 3;
                cleanColors(chageIcon);
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_forService.setTextColor(getResources().getColor(R.color.textColor));
        tv_forService1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_toEvaluate.setTextColor(getResources().getColor(R.color.textColor));
        tv_toEvaluate1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_completed1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_all.setTextColor(getResources().getColor(R.color.textColor));
        tv_all1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_forService.setTextColor(getResources().getColor(R.color.greenColors));
            tv_forService1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment);
        } else if (chageIcon == 1) {
            tv_toEvaluate.setTextColor(getResources().getColor(R.color.greenColors));
            tv_toEvaluate1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment1);
        } else if (chageIcon == 2) {
            tv_completed.setTextColor(getResources().getColor(R.color.greenColors));
            tv_completed1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment2);
        } else if (chageIcon == 3) {
            tv_all.setTextColor(getResources().getColor(R.color.greenColors));
            tv_all1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment3);
        } else {
            tv_forService.setTextColor(getResources().getColor(R.color.greenColors));
            tv_forService1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment);
        }
    }
}

