package com.leo.demo.threadtest.tasktest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TaskTest
 * @Description: ${description}
 * @Author: leo825
 * @Date: 2020-01-17 16:33
 * @Version: 1.0
 */
public class TaskTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        for (int i = 1; i <= 3; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new MyTimerTask("【MyTimerTask-" + i + "】"), 0, i, TimeUnit.SECONDS);
        }
        //scheduledExecutorService.shutdown();
    }


    public static String formateDateNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
