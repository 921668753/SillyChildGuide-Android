package com.common.cklibrary.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * 重新写了部分内容ck  源于 KJ
 * Created by Administrator on 2017/2/12.
 */

public class KJActivityStack {
    private static Stack<I_KJActivity> activityStack;
    private static final KJActivityStack instance = new KJActivityStack();

    private KJActivityStack() {
    }

    public static KJActivityStack create() {
        return instance;
    }

    /**
     * 获取当前Activity栈中元素个数
     */
    public int getCount() {
        return activityStack.size();
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(I_KJActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public Activity topActivity() {
        if (activityStack == null) {
            throw new NullPointerException(
                    "Activity stack is Null,your Activity must extend KJActivity");
        }
        if (activityStack.isEmpty()) {
            return null;
        }
        I_KJActivity activity = activityStack.lastElement();
        return (Activity) activity;
    }

    /**
     * 获取栈底ActivityActivity（栈底Activity）
     */
    public Activity bottomActivity() {
        if (activityStack == null) {
            throw new NullPointerException(
                    "Activity stack is Null,your Activity must extend KJActivity");
        }
        if (activityStack.isEmpty()) {
            return null;
        }
        I_KJActivity activity = activityStack.firstElement();
        return (Activity) activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        I_KJActivity activity = null;
        for (I_KJActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return (Activity) activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        I_KJActivity activity = activityStack.lastElement();
        finishActivity((Activity) activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();//此处不用finish
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) != null && activityStack.get(i).getClass().equals(cls)) {
                finishActivity((Activity) activityStack.get(i));
                break;
            }
        }


//        for (I_KJActivity activity : activityStack) {
//            if (activity != null && activity.getClass().equals(cls)) {
//                finishActivity((Activity) activity);
//            }
//        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) != null && !(activityStack.get(i).getClass().equals(cls))) {
                finishActivity((Activity) activityStack.get(i));
                i--;
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity
     *
     * @param cls
     */
    public void finishToThis(Class<?> cls, Class<?> cls1) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (i == (activityStack.size() - 1)) {
                Activity activity = (Activity) activityStack.get(i);
                activityStack.clear();
                Intent intent = new Intent(activity, cls);
                activity.startActivity(intent);
                activity.finish();
            } else {
                if (activityStack.get(i) != null && !activityStack.get(i).getClass().equals(cls1)) {
                    ((Activity) activityStack.get(i)).finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                ((Activity) activityStack.get(i)).finish();
            }
        }
        activityStack.clear();
    }

    @Deprecated
    public void AppExit(Context cxt) {
        appExit(cxt);
    }

    /**
     * 应用程序退出
     */
    @Deprecated
    public void appExit(Context context) {
        try {
            finishAllActivity();
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            Runtime.getRuntime().exit(-1);
        }
    }
}
