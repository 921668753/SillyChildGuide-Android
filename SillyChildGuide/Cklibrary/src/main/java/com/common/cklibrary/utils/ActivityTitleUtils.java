

package com.common.cklibrary.utils;

import android.support.v7.app.AppCompatActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;


/**
 * This provides methods to help Activities load their UI.
 */
public  class ActivityTitleUtils {
    /**
     * Configure Toolbar
     */
    public static void initToolbar(AppCompatActivity appCompatActivity,
                                   String title, boolean displayHomeAsUp, int id) {
        initToolbar(appCompatActivity, title, true, displayHomeAsUp, id);
    }

    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, boolean asSupportActionbar, boolean displayHomeAsUp, int id) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setTitleText(title);
        titlebar.setRightText("");
        titlebar.setRightDrawable(null);
        titlebar.setDelegate(new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                appCompatActivity.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                //  ViewInject.toast("right");
            }
        });
    }

    public static void initToolbar(AppCompatActivity appCompatActivity,
                                   String title, String rightText, int id, BGATitleBar.SimpleDelegate simpleDelegate) {
        initToolbar(appCompatActivity, title, rightText, true, id, simpleDelegate);
    }

    /**
     * 设置右边按钮
     *
     * @param appCompatActivity
     * @param title
     * @param id
     * @param simpleDelegate
     */

    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, String rightText, boolean asSupportActionbar, int id, BGATitleBar.SimpleDelegate simpleDelegate) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(null);
        titlebar.setDelegate(simpleDelegate);

    }

    @SuppressWarnings("deprecation")
    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, String rightText,int rightRes ,int id, BGATitleBar.SimpleDelegate simpleDelegate) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(appCompatActivity.getResources().getDrawable(rightRes));
        titlebar.setDelegate(simpleDelegate);

    }



    /**
     * 更改左边图标
     */
    public static void initToolbar1(final AppCompatActivity appCompatActivity, int liftImg, String liftText, int titltImg,
                                    String titleText, int rightImg, String rightText, final boolean liftClick, boolean tittleClick, boolean rightClick, int id) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setLeftDrawable(liftImg);
        titlebar.setLeftText(liftText);
        titlebar.setTitleDrawable(titltImg);
        titlebar.setTitleText(titleText);
        titlebar.setRightDrawable(rightImg);
        titlebar.setRightText(rightText);
        titlebar.setDelegate(new BGATitleBar.Delegate() {
            @Override
            public void onClickLeftCtv() {
                if (liftClick) {
                    appCompatActivity.finish();
                }
            }

            @Override
            public void onClickTitleCtv() {
            }

            @Override
            public void onClickRightCtv() {
                //  ViewInject.toast("right");
            }

            @Override
            public void onClickRightSecondaryCtv() {

            }
        });
    }
}
