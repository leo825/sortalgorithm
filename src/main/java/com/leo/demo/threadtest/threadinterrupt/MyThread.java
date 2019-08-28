package com.leo.demo.threadtest.threadinterrupt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @Date 2019/7/29 20:27
 * @TODO
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        getThreadLog("线程开始执行了。。。");
        int needRunTime = getRunningTime();
        getThreadLog("线程执行时间为：" + needRunTime + "秒");
        try {
            Thread.sleep(needRunTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getThreadLog("线程执行了结束了！！！");
    }

    /**
     * 获取线程名和时间
     *
     * @return
     */
    public void getThreadLog(String logContent) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(Thread.currentThread().getName());
        stringBuffer.append(" ");
        stringBuffer.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        stringBuffer.append("]");
        stringBuffer.append(logContent);
        System.out.println(stringBuffer.toString());
    }

    /**
     * 获取线程运行时间
     */
    public int getRunningTime() {
        //生成一个随机数
        return new Random().nextInt(10);
    }
}
