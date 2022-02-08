package com.brins.litemusic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.multidex.MultiDex;
import com.brins.base.executor.ThreadExecutor;
import com.brins.base.manager.activity.CurrentActivityManager;
import com.brins.base.utils.Logz;
import dagger.hilt.android.HiltAndroidApp;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
@HiltAndroidApp
public class AppShell extends Application {

    private ApplicationLike mApplicationLike;
    private int resumeActivityCount;
    private boolean isRunInBackground;

    public AppShell() {
        mApplicationLike = new LiteMusicApplication(this);
    }

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public boolean isRunInBackground() {
        return isRunInBackground;
    }

    @Override public void onCreate() {
        super.onCreate();
        mApplicationLike.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logz.d("AppShell,onActivityCreated activity name is:" + activity.getClass()
                    .getSimpleName());

                if (activity.getResources() != null
                    && activity.getResources().getConfiguration() != null) {
                    Configuration newConfig = activity.getResources().getConfiguration();
                }
            }

            @Override public void onActivityStarted(Activity activity) {
                resumeActivityCount++;
                if (isRunInBackground) {
                    //应用从后台进入前台
                    backToApp(activity);
                }
            }

            @Override public void onActivityResumed(Activity activity) {
                Logz.d("AppShell,onActivityResumed activity name is:" + activity.getClass()
                    .getSimpleName());
                ThreadExecutor.IO.execute(new Runnable() {
                    @Override public void run() {
                        // todo 服务类ModuleService获取

                        CurrentActivityManager.getInstance().setCurrentActivity(activity);
                        CurrentActivityManager.getInstance().setIsApplicationVisible(true);
                    }
                });
            }

            @Override public void onActivityPaused(Activity activity) {

                ThreadExecutor.IO.execute(new Runnable() {
                    @Override public void run() {
                        CurrentActivityManager.getInstance().setIsApplicationVisible(false);
                    }
                });
            }

            @Override public void onActivityStopped(Activity activity) {
                resumeActivityCount--;
                if (resumeActivityCount == 0) {
                    leaveApp(activity);
                }
            }

            @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mApplicationLike.onConfigurationChanged(newConfig);
    }

    @Override public void onLowMemory() {
        super.onLowMemory();
        mApplicationLike.onLowMemory();
    }

    @Override public void onTerminate() {
        super.onTerminate();
        mApplicationLike.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mApplicationLike.onTrimMemory(level);
    }

    private void backToApp(Activity activity) {
        Logz.d("back2App " + activity.getClass().getSimpleName());
        isRunInBackground = true;
        //后面要执行什么操作，暂时没想好
    }

    private void leaveApp(Activity activity) {
        Logz.d("leaveApp " + activity.getClass().getSimpleName());
        isRunInBackground = true;
        CurrentActivityManager.getInstance().setLastBackActivity(activity);

    }
}
