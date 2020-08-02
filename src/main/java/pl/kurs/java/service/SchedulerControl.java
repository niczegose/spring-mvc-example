package pl.kurs.java.service;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchedulerControl {
    private final ScheduledExecutorService executorService;
    private long interval;
    private TimeUnit timeUnit;
    private ScheduledFuture<?> scheduleHandle;
    private Scheduler scheduler;
    private Map<String, Exchanger> exchangerMap;

    public SchedulerControl(Map<String, Exchanger> exchangerMap) {
        this.executorService = Executors.newScheduledThreadPool(1);
        interval = 10;
        timeUnit = TimeUnit.SECONDS;
        this.exchangerMap = exchangerMap;
        scheduler = new Scheduler(exchangerMap);
    }

    public void start() {
        scheduleHandle = executorService.scheduleAtFixedRate(scheduler, 0, interval, timeUnit);
    }

    public void start(long interval, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.interval = interval;
        this.start();
    }

    public void setSchedule(TimeUnit timeUnit, long interval) {
        if (scheduleHandle != null) {
            scheduleHandle.cancel(true);
        }

        this.timeUnit = timeUnit;
        this.interval = interval;
        scheduleHandle = executorService.scheduleAtFixedRate(scheduler, 0, interval, timeUnit);
    }

    public void stop() {
        if (scheduleHandle != null) {
            scheduleHandle.cancel(true);
        }
        executorService.shutdown();
    }
}