package com.common.cklibrary.common;

/**
 * Created by Administrator on 2017/3/7.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 规范Activity跳转的接口协议
 *
 * @author kymjs (https://github.com/kymjs)
 */
public interface I_SkipActivity {
    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls);

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Intent it);

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Intent it);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivityForResult(Activity aty, Class<?> cls, int requestcode);
}
