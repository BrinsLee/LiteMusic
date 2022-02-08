package com.brins.litemusic;

import android.app.Application;
import android.content.res.Configuration;
import com.brins.litemusic.app.Profile;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public class LiteMusicApplication implements ApplicationLike{

    private static final String PLUGIN_TABLES = "com.huanliao.applike.impl";
    protected Application mApplication;
    private Profile profile;

    public LiteMusicApplication(Application application) {
        mApplication = application;
    }

    @Override public void onCreate() {

    }

    @Override public void onLowMemory() {

    }

    @Override public void onTrimMemory(int paramInt) {

    }

    @Override public void onTerminate() {

    }

    @Override public void onConfigurationChanged(Configuration paramConfiguration) {

    }
}
