package com.brins.base.manager.activity;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public class CurrentActivityManager {
    private boolean isApplicationVisible = false;

    public boolean isIsApplicationVisible() {
        return isApplicationVisible;
    }

    public void setIsApplicationVisible(boolean isVisible) {
        isApplicationVisible = isVisible;
    }

    private static CurrentActivityManager sInstance = new CurrentActivityManager();

    private WeakReference<Activity> sCurrentActivityWeakRef;

    private WeakReference<Activity> sLastBackActivityWeakRef;

    private CurrentActivityManager() {

    }

    public static CurrentActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    private static Stack mActivityStack;
    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public void setLastBackActivity(Activity lastBackActivity) {
        this.sLastBackActivityWeakRef = new WeakReference<>(lastBackActivity);
    }

    public Activity getLastBackActivity() {
        Activity activity = null;
        if (sLastBackActivityWeakRef != null) {
            activity = sLastBackActivityWeakRef.get();
        }
        return activity;
    }

    /**
     * 弹出栈顶Activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获得当前栈顶Activity
     */
    public Activity currentActivity() {
        //lastElement()获取最后个子元素，这里是栈顶的Activity
        if(mActivityStack == null || mActivityStack.size() ==0){
            return null;
        }
        Activity activity = (Activity) mActivityStack.lastElement();
        return activity;
    }

    /**
     * 将当前Activity推入栈中
     */
    public void pushActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack();
        }
        mActivityStack.add(activity);
    }

    /**
     * 弹出指定的class所在栈顶的所有Activity
     * @class 指定的类
     */
    public void popTopActivities(Class clazz) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(clazz)) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 弹出栈中所有Activity
     */
    public void popAllActivitys() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }
}
