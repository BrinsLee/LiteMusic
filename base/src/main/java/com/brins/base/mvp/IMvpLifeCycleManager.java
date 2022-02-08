package com.brins.base.mvp;



/**
 * Created by kemp on 2018/2/27.
 */

public interface IMvpLifeCycleManager {
    void addMvpLifeCycle(MvpLifeCycle lifeCycle);

    void removeMvpLifeCycle(MvpLifeCycle lifeCycle);

    void onDestroyMvpLifeCycle();

    boolean isLifeCycleDestroy();

    void setLifeCycleDestroy(boolean isDestroy);
}
