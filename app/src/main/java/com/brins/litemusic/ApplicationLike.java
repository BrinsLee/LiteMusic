package com.brins.litemusic;

import android.content.res.Configuration;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public interface ApplicationLike {
    void onCreate();

    void onLowMemory();

    void onTrimMemory(int paramInt);

    void onTerminate();

    void onConfigurationChanged(Configuration paramConfiguration);

}
