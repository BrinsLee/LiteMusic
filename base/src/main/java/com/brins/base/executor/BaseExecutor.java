package com.brins.base.executor;

import java.util.Date;
import java.util.Timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延迟执行基础类
 */
abstract class BaseExecutor {

    protected ScheduledExecutorService scheduledExecutorService;

    BaseExecutor() {
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    FxTimerTask scheduleTask(Runnable task, long delay) {
        FxTimerTask timerTask = getTimeTask(task);
        scheduledExecutorService.schedule(task, delay, TimeUnit.MILLISECONDS);
        return timerTask;
    }

    FxTimerTask scheduleTask(Runnable task, Date date) {
        FxTimerTask timerTask = getTimeTask(task);
        scheduledExecutorService.schedule(timerTask, date.getTime(), TimeUnit.MILLISECONDS);
        return timerTask;
    }

    abstract FxTimerTask getTimeTask(Runnable task);
}
