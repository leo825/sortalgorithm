package com.leo.demo.threadtest.tasktest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TimerTest
 * @Description: 测试Timer
 * @Author: leo825
 * @Date: 2020-01-19 10:02
 * @Version: 1.0
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "我是一个定时任务");
                try {
                    for (int i = 0; i < 20; i++) {
                        if (i == 5) {
                            int z = i / 0;
                        }
                        System.out.println("模拟业务执行时间");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 2000);
    }
}
