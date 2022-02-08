package com.brins.base.mvp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kemp on 2018/2/27.
 */

public class MvpLifeCycleManager implements IMvpLifeCycleManager {
    private final Object LIFE_LOCK = new Object();
    private ArrayList<MvpLifeCycle> mLifeCycles;
    private boolean isDestroy = false;

    public MvpLifeCycleManager() {
        mLifeCycles = new ArrayList<>();
    }



    @Override
    public void addMvpLifeCycle(MvpLifeCycle lifeCycle) {
        synchronized (LIFE_LOCK) {
            mLifeCycles.add(lifeCycle);
        }
    }

    @Override
    public void removeMvpLifeCycle(MvpLifeCycle lifeCycle) {
        synchronized (LIFE_LOCK) {
            mLifeCycles.remove(lifeCycle);
        }
    }

    @Override
    public void onDestroyMvpLifeCycle() {
        List<MvpLifeCycle> temp;

        synchronized (LIFE_LOCK) {
            // 避免 ConcurrentModificationException
            temp = new ArrayList<>(mLifeCycles);
        }

        if (!temp.isEmpty()) {
            for (MvpLifeCycle item : temp) {
                item.onLifeDestroy(); // BasePresenter.onLifeDestroy 会调用 removeLiveMvpLifeCycle
            }
        }

        synchronized (LIFE_LOCK) {
            mLifeCycles.clear();
        }
    }

    @Override
    public boolean isLifeCycleDestroy() {
        return isDestroy;
    }

    @Override
    public void setLifeCycleDestroy(boolean isDestroy) {
        this.isDestroy = isDestroy;
    }
}
