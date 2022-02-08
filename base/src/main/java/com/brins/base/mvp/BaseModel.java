package com.brins.base.mvp;

import androidx.annotation.CallSuper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/25.
 */

public class BaseModel implements IMvpLifeCycleManager, IBaseModel {
    private IMvpLifeCycleManager mLifeCycleManager;

    public BaseModel() {
        super();
        mLifeCycleManager = new MvpLifeCycleManager();
    }

    /**
     * 执行网络请求
     * @param observable
     * @param observer
     */
    public void subscribe(Observable observable, Observer observer) {
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
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

    @CallSuper
    @Override
    public void onDestroy() {
        setLifeCycleDestroy(true);
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
}
