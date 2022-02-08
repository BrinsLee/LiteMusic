package com.brins.base.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public interface IExecutor {

    void execute(Runnable task);

    FxTimerTask schedule(Runnable task, long delay);

    FxTimerTask schedule(Runnable task, Date time);

    Future<?> submit(Runnable task);

    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);
}
