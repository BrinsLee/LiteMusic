package com.brins.base.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public enum ThreadExecutor implements IExecutor {

    /**
     * 主进程，即UI进程，不分线程,不并发
     */
    MAIN(new MainThreadExecutor()),

    /**
     * IO线程，用于写文件、IO等较大的任务，在一个线程数为CPU数两倍的线程池中执行
     */
    IO(new IOThreadExecutor()),

    /**
     * 后台线程，用于执行非常小的任务，不分线程,不并发
     */
    BACKGROUND(new BackgroundThreadExecutor()),

    /**
     * 异步线程，用于执行较小的任务，在一个无上限的线程池执行
     */
    ASYNC(new AsyncThreadExecutor());

    final private IExecutor executor;

    ThreadExecutor(IExecutor executor) {
        this.executor = executor;
    }

    /**
     * 执行task，不关心结果，提交task后不可控制
     * @throws NullPointerException  task不可为空
     */
    @Override public void execute(Runnable task) {
        executor.execute(task);
    }

    /**
     * 延时执行，不关心结果，提交task后取消
     * @param delay 延迟毫秒数
     * @return {@link FxTimerTask} 可用于取消尚在等待的任务,已执行不可取消
     * @throws NullPointerException task不可为空
     */
    @Override public FxTimerTask schedule(Runnable task, long delay) {
        return executor.schedule(task, delay);
    }

    /**
     * 延时执行，不关心结果，提交task后取消
     * @param time 实际执行的时间点
     * @throws NullPointerException task不可为空
     */
    @Override public FxTimerTask schedule(Runnable task, Date time) {
        return executor.schedule(task, time);
    }

    /**
     * 执行task，不关心结果，提交task后可取消，可查询状态
     * @return {@link Future} 执行中的任务通过interrupt机制取消
     *  @throws NullPointerException task不可为空
     */
    @Override
    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    /**
     * 执行task，关心结果，提交task后可取消，可查询状态
     * @throws NullPointerException task不可为空
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    /**
     *  执行task，关心结果，提交task后可取消，可查询状态
     * @param result 结果
     * @throws NullPointerException task不可为空
     */
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return executor.submit(task, result);
    }
}
