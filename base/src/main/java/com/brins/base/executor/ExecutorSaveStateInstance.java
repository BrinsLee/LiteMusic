package com.brins.base.executor;

import java.util.Date;
import java.util.concurrent.FutureTask;

class ExecutorSaveStateInstance {

    static class Execute extends ExecutorSaveStateInstance {

        Runnable task;

        Execute(Runnable task) {
            this.task = task;
        }
    }

    static class ScheduleDelay extends ExecutorSaveStateInstance {

        FxTimerTask task;

        long delay;

        ScheduleDelay(FxTimerTask task, long delay) {
            this.task = task;
            this.delay = delay;
        }
    }

    static class ScheduleDate extends ExecutorSaveStateInstance {

        FxTimerTask task;

        Date time;

        ScheduleDate(FxTimerTask task, Date time) {
            this.task = task;
            this.time = time;
        }
    }

    static class Submit extends ExecutorSaveStateInstance {

        FutureTask futureTask;

        Submit(FutureTask futureTask) {
            this.futureTask = futureTask;
        }
    }
}
