package com.brins.base.executor;

import java.util.TimerTask;

public abstract class FxTimerTask extends TimerTask {

    private boolean isCancelled = false;

    @Override
    public boolean cancel() {
        isCancelled = true;
        return super.cancel();
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
