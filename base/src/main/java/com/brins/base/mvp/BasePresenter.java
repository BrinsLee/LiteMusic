package com.brins.base.mvp;

import android.content.Context;
import androidx.annotation.CallSuper;

/**
 * Created on 2017/11/15
 *
 * @author zhengjianhui@lizhi.fm
 */

public abstract class BasePresenter implements IBasePresenter, IMvpLifeCycleManager {
    private IMvpLifeCycleManager mLifeCycleManager;

    public BasePresenter() {
        mLifeCycleManager = new MvpLifeCycleManager();
    }

    @Override
    public void init(Context context) {
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mLifeCycleManager.setLifeCycleDestroy(true);
        onDestroyMvpLifeCycle();
    }

    @Override
    public void addMvpLifeCycle(MvpLifeCycle lifeCycle) {
        mLifeCycleManager.addMvpLifeCycle(lifeCycle);
    }

    @Override
    public void removeMvpLifeCycle(MvpLifeCycle lifeCycle) {
        mLifeCycleManager.removeMvpLifeCycle(lifeCycle);
    }

    @Override
    public void onDestroyMvpLifeCycle() {
        mLifeCycleManager.onDestroyMvpLifeCycle();
    }

    @Override
    public boolean isLifeCycleDestroy() {
        return mLifeCycleManager.isLifeCycleDestroy();
    }

    @Override
    public void setLifeCycleDestroy(boolean isDestroy) {
        mLifeCycleManager.setLifeCycleDestroy(isDestroy);
    }

    /**
     * 停止逻辑执行，比如在View 暂停进入后台时候等等，可以停止逻辑的执行
     */
    @Override
    public void onStopLogic() {

    }


    /**
     * 开始逻辑执行，比如View 进入前台时候等等，重新开始执行逻辑
     */
    @Override
    public void onStartLogic() {

    }
}
