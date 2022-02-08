package com.brins.base.executor;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
class MainThreadExecutor extends BaseExecutor implements IExecutor, Handler.Callback {

    private Handler handler = new Handler(Looper.getMainLooper(), this);


    @Override public void execute(Runnable task) {
        handler.post(task);
    }

    @Override public FxTimerTask schedule(Runnable task, long delay) {
        return scheduleTask(task, delay);
    }

    @Override public FxTimerTask schedule(Runnable task, Date time) {
        return scheduleTask(task, time);
    }

    @Override public Future<?> submit(Runnable task) {
        Message msg = Message.obtain();
        FutureTask<Void> futureTask = new FutureTask<>(task, null);
        msg.obj = futureTask;
        handler.sendMessage(msg);
        return futureTask;
    }

    @Override public <T> Future<T> submit(Callable<T> task) {
        Message msg = Message.obtain();
        FutureTask<T> futureTask = new FutureTask<>(task);
        msg.obj = futureTask;
        handler.sendMessage(msg);
        return futureTask;
    }

    @Override public <T> Future<T> submit(Runnable task, T result) {
        Message msg = Message.obtain();
        FutureTask<T> futureTask = new FutureTask<>(task, result);
        msg.obj = futureTask;
        handler.sendMessage(msg);
        return futureTask;
    }

    @Override FxTimerTask getTimeTask(final Runnable task) {
        return new FxTimerTask() {
            @Override public void run() {
                execute(task);
            }
        };
    }

    @Override public boolean handleMessage(Message msg) {
        if (msg.obj instanceof FutureTask) {
            FutureTask futureTask = (FutureTask) msg.obj;
            futureTask.run();
            return true;
        }
        return false;
    }
}
