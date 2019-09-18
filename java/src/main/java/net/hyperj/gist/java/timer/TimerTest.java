package net.hyperj.gist.java.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + new Date());
            }
        };
        long delay = 1000L;
        long period = 1000L;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(period * 5);
        executor.shutdown();
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
        Thread.sleep(period * 5);
        timer.cancel();
    }
}
