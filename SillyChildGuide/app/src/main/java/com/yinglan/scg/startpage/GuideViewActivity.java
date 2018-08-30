package com.yinglan.scg.startpage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.yinglan.scg.R;
import com.yinglan.scg.main.MainActivity;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 引导页
 * 根据项目需求  可有可无
 * Created by ruitu ck on 2016/9/14.
 */

public class GuideViewActivity extends BaseActivity {


    @BindView(id = R.id.banner_guide_foreground)
    private BGABanner mForegroundBanner;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_guideview);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setListener();
        processLogic();
    }

    private void setListener() {
        // 监听页码切换事件，控制「跳过按钮」和「进入主界面的显示与隐藏」
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                skipActivity(aty, MainActivity.class);
            }
        });
    }

    private void processLogic() {
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
        mForegroundBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        //mForegroundBanner.setData(Arrays.asList(R.mipmap.guide_one, R.mipmap.guide_two, R.mipmap.guide_three), null);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_guide_skip:
                skipActivity(this, MainActivity.class);
                break;
            case R.id.btn_guide_enter:
                skipActivity(this, MainActivity.class);
                break;
        }
    }

    /**
     * 禁止退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }
}
