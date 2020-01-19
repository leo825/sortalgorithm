package com.leo.demo.threadtest.tasktest;

import java.util.TimerTask;

import static com.leo.demo.threadtest.tasktest.TaskTest.formateDateNow;

/**
 * @ClassName: MyTimerTask
 * @Description: ${description}
 * @Author: leo825
 * @Date: 2020-01-19 15:25
 * @Version: 1.0
 */
public class MyTimerTask extends TimerTask {

    private String timerName;

    public String getTimerName() {
        return timerName;
    }

    public void setTimerName(String timerName) {
        this.timerName = timerName;
    }

    public MyTimerTask(String timerName) {
        this.timerName = timerName;
    }

    @Override
    public void run() {
        System.out.println(this.getTimerName() + " 开始工作了" + formateDateNow());
    }
}
