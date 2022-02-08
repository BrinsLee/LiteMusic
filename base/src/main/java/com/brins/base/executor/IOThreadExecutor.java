package com.brins.base.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * IO线程池，用于执行较大任务
 */
class IOThreadExecutor extends BaseExecutor implements IExecutor{

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_THREAD = CPU_COUNT * 2;

    private ExecutorService executor = Executors.newFixedThreadPool(CORE_THREAD);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public FxTimerTask schedule(Runnable task, long delay) {
        return scheduleTask(task, delay);
    }

    @Override
    public FxTimerTask schedule(Runnable task, Date time) {
        return scheduleTask(task, time);
    }

    @Override
    FxTimerTask getTimeTask(final Runnable task) {
        return new FxTimerTask() {
            @Override
            public void run() {
                execute(task);
            }
        };
    }

    @Override
    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task){
        return executor.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return executor.submit(task, result);
    }
}
