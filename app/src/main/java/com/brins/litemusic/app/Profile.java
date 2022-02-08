package com.brins.litemusic.app;

import android.app.Application;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public abstract class Profile {

    protected Application application;

    public Profile(Application application) {
        this.application = application;
    }

    public abstract void onCreate();
}
