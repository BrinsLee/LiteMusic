package com.brins.base.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public class AsyncThreadExecutor extends BaseExecutor implements IExecutor{

    private ExecutorService executor  = Executors.newCachedThreadPool();


    @Override FxTimerTask getTimeTask(final Runnable task) {
        return new FxTimerTask() {
            @Override
            public void run() {
                execute(task);
            }
        };
    }

    @Override public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override public FxTimerTask schedule(Runnable task, long delay) {
        return scheduleTask(task, delay);
    }

    @Override public FxTimerTask schedule(Runnable task, Date time) {
        return scheduleTask(task, time);
    }

    @Override public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    @Override public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    @Override public <T> Future<T> submit(Runnable task, T result) {
        return executor.submit(task, result);
    }
}
